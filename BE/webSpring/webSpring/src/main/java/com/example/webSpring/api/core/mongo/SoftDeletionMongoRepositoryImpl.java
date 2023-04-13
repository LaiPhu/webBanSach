
package com.example.webSpring.api.core.mongo;

import com.example.webSpring.api.core.Constants;
import com.mongodb.client.result.DeleteResult;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.util.StreamUtils;
import org.springframework.data.util.Streamable;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class SoftDeletionMongoRepositoryImpl<T, ID> implements SoftDeletionMongoRepository<T, ID> {

    private static final boolean DEFAULT_SOFT_DELETION = Constants.SoftDeletion.DEFAULT_OPERATOR;
    private final MongoOperations mongoOperations;
    private final MongoEntityInformation<T, ID> entityInformation;

    public SoftDeletionMongoRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        Assert.notNull(metadata, "MongoEntityInformation must not be null!");
        Assert.notNull(mongoOperations, "MongoOperations must not be null!");
        this.entityInformation = metadata;
        this.mongoOperations = mongoOperations;
    }

    // -------------------------------------------------------------------------
    // Methods from CrudRepository
    // -------------------------------------------------------------------------

    private static <E> Collection<E> toCollection(Iterable<E> ids) {
        return ids instanceof Collection
            ? (Collection<E>) ids
            : StreamUtils.createStreamFromIterator(ids.iterator()).collect(Collectors.toList());
    }

    @Override
    public <S extends T> S save(S entity) {
        Assert.notNull(entity, "Entity must not be null!");
        if (entityInformation.isNew(entity)) {
            return mongoOperations.insert(entity, entityInformation.getCollectionName());
        }
        return mongoOperations.save(entity, entityInformation.getCollectionName());
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        Streamable<S> source = Streamable.of(entities);
        boolean allNew = source.stream().allMatch(entityInformation::isNew);
        if (allNew) {
            List<S> result = source.stream().collect(Collectors.toList());
            return new ArrayList<>(mongoOperations.insert(result, entityInformation.getCollectionName()));
        }
        return source.stream().map(this::save).collect(Collectors.toList());
    }

    @Override
    public Optional<T> findById(ID id) {
        return findById(id, DEFAULT_SOFT_DELETION);
    }

    @Override
    public Optional<T> findById(ID id, boolean ignoreSoftDeletion) {
        Assert.notNull(id, "The given id must not be null!");
        return Optional.ofNullable(
            ignoreSoftDeletion
                ? mongoOperations.findById(id, entityInformation.getJavaType(), entityInformation.getCollectionName())
                : mongoOperations.findOne(getIdQuery(id), entityInformation.getJavaType(), entityInformation.getCollectionName())
        );
    }

    @Override
    public boolean existsById(ID id) {
        return existsById(id, DEFAULT_SOFT_DELETION);
    }

    @Override
    public boolean existsById(ID id, boolean ignoreSoftDeletion) {
        Assert.notNull(id, "The given id must not be null!");
        return mongoOperations.exists(
            getIdQuery(id, ignoreSoftDeletion),
            entityInformation.getJavaType(),
            entityInformation.getCollectionName()
        );
    }

    @Override
    public List<T> findAll() {
        return findAll(DEFAULT_SOFT_DELETION);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return findAllById(ids);
    }

//    @Override
//    public Iterable<T> findAllById(Iterable<ID> ids) {
//        return findAllById(ids, DEFAULT_SOFT_DELETION);
//    }

    @Override
    public List<T> findAll(boolean ignoreSoftDeletion) {
        return findAll(new Query(), ignoreSoftDeletion);
    }


    @Override
    public Iterable<T> findAllById(Iterable<ID> ids, boolean ignoreSoftDeletion) {
        Assert.notNull(ids, "The given Ids of entities not be null!");
        return findAll(getIdQuery(ids), ignoreSoftDeletion);
    }

    @Override
    public long count() {
        return count(DEFAULT_SOFT_DELETION);
    }

    @Override
    public long count(boolean ignoreSoftDeletion) {
        Query query = new Query();
        if (!ignoreSoftDeletion) {
            SoftDeletionUtils.queryWithNotDeleted(query, entityInformation.getJavaType());
        }
        return mongoOperations.count(query, entityInformation.getCollectionName());
    }

    @Override
    public void deleteById(ID id) {
        deleteById(id, DEFAULT_SOFT_DELETION);
    }

    @Override
    public void deleteById(ID id, boolean ignoreSoftDeletion) {
        Assert.notNull(id, "The given id must not be null!");
        Query query = getIdQuery(id, ignoreSoftDeletion);
        if (ignoreSoftDeletion) {
            mongoOperations.remove(query, entityInformation.getJavaType(), entityInformation.getCollectionName());
        } else {
            mongoOperations.updateFirst(
                query,
                SoftDeletionUtils.delete(),
                entityInformation.getJavaType(),
                entityInformation.getCollectionName()
            );
        }
    }

    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "The given entity must not be null!");
        DeleteResult deleteResult = mongoOperations.remove(entity, entityInformation.getCollectionName());
        if (entityInformation.isVersioned() && deleteResult.wasAcknowledged() && deleteResult.getDeletedCount() == 0) {
            throw new OptimisticLockingFailureException(
                String.format(
                    "The entity with id %s with version %s in %s cannot be deleted! Was it modified or deleted in the meantime?",
                    entityInformation.getId(entity),
                    entityInformation.getVersion(entity),
                    entityInformation.getCollectionName()
                )
            );
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        deleteAllById(ids, DEFAULT_SOFT_DELETION);
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids, boolean ignoreSoftDeletion) {
        Assert.notNull(ids, "The given Iterable of ids must not be null!");
        Query query = getIdQuery(ids, ignoreSoftDeletion);
        if (ignoreSoftDeletion) {
            mongoOperations.remove(query, entityInformation.getJavaType(), entityInformation.getCollectionName());
        } else {
            mongoOperations.updateMulti(
                query,
                SoftDeletionUtils.delete(),
                entityInformation.getJavaType(),
                entityInformation.getCollectionName()
            );
        }
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        deleteAll(DEFAULT_SOFT_DELETION);
    }

    // -------------------------------------------------------------------------
    // Methods from PagingAndSortingRepository
    // -------------------------------------------------------------------------

    @Override
    public void deleteAll(boolean ignoreSoftDeletion) {
        if (ignoreSoftDeletion) {
            mongoOperations.remove(new Query(), entityInformation.getCollectionName());
        } else {
            mongoOperations.updateMulti(
                new Query(),
                SoftDeletionUtils.delete(),
                entityInformation.getJavaType(),
                entityInformation.getCollectionName()
            );
        }
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return findAll(pageable, DEFAULT_SOFT_DELETION);
    }

    @Override
    public Page<T> findAll(Pageable pageable, boolean ignoreSoftDeletion) {
        Assert.notNull(pageable, "Pageable must not be null!");
        long count = count(ignoreSoftDeletion);
        List<T> list = findAll(new Query().with(pageable), ignoreSoftDeletion);
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public List<T> findAll(Sort sort) {
        return findAll(sort, DEFAULT_SOFT_DELETION);
    }

    // -------------------------------------------------------------------------
    // Methods from MongoRepository
    // -------------------------------------------------------------------------

    @Override
    public List<T> findAll(Sort sort, boolean ignoreSoftDeletion) {
        Assert.notNull(sort, "Sort must not be null!");
        return findAll(new Query().with(sort), ignoreSoftDeletion);
    }

    @Override
    public <S extends T> S insert(S entity) {
        Assert.notNull(entity, "Entity must not be null!");
        return mongoOperations.insert(entity, entityInformation.getCollectionName());
    }

    // -------------------------------------------------------------------------
    // Methods from QueryByExampleExecutor
    // -------------------------------------------------------------------------

    @Override
    public <S extends T> List<S> insert(Iterable<S> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        Collection<S> list = toCollection(entities);
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList<>(mongoOperations.insertAll(list));
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return findOne(example, DEFAULT_SOFT_DELETION);
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example, boolean ignoreSoftDeletion) {
        Assert.notNull(example, "Sample must not be null!");
        Query query = new Query(new Criteria().alike(example)).collation(entityInformation.getCollation());
        if (!ignoreSoftDeletion) {
            SoftDeletionUtils.queryWithNotDeleted(query, entityInformation.getJavaType());
        }
        return Optional.ofNullable(mongoOperations.findOne(query, example.getProbeType(), entityInformation.getCollectionName()));
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return findAll(example, DEFAULT_SOFT_DELETION);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, boolean ignoreSoftDeletion) {
        return findAll(example, Sort.unsorted(), ignoreSoftDeletion);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return findAll(example, sort, DEFAULT_SOFT_DELETION);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort, boolean ignoreSoftDeletion) {
        Assert.notNull(example, "Sample must not be null!");
        Assert.notNull(sort, "Sort must not be null!");
        Query query = new Query(new Criteria().alike(example)) //
            .collation(entityInformation.getCollation()) //
            .with(sort);
        if (!ignoreSoftDeletion) {
            SoftDeletionUtils.queryWithNotDeleted(query, entityInformation.getJavaType());
        }
        return mongoOperations.find(query, example.getProbeType(), entityInformation.getCollectionName());
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return findAll(example, pageable, DEFAULT_SOFT_DELETION);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable, boolean ignoreSoftDeletion) {
        Assert.notNull(example, "Sample must not be null!");
        Assert.notNull(pageable, "Pageable must not be null!");
        Query query = new Query(new Criteria().alike(example)) //
            .collation(entityInformation.getCollation())
            .with(pageable); //
        if (!ignoreSoftDeletion) {
            SoftDeletionUtils.queryWithNotDeleted(query, entityInformation.getJavaType());
        }
        List<S> list = mongoOperations.find(query, example.getProbeType(), entityInformation.getCollectionName());
        return org.springframework.data.support.PageableExecutionUtils.getPage(
            list,
            pageable,
            () -> mongoOperations.count(Query.of(query).limit(-1).skip(-1), example.getProbeType(), entityInformation.getCollectionName())
        );
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return count(example, DEFAULT_SOFT_DELETION);
    }

    @Override
    public <S extends T> long count(Example<S> example, boolean ignoreSoftDeletion) {
        Assert.notNull(example, "Sample must not be null!");
        Query query = new Query(new Criteria().alike(example)) //
            .collation(entityInformation.getCollation());
        if (!ignoreSoftDeletion) {
            SoftDeletionUtils.queryWithNotDeleted(query, entityInformation.getJavaType());
        }
        return mongoOperations.count(query, example.getProbeType(), entityInformation.getCollectionName());
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return exists(example, DEFAULT_SOFT_DELETION);
    }

    @Override
    public <S extends T, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    // -------------------------------------------------------------------------
    // Utility methods
    // -------------------------------------------------------------------------

    @Override
    public <S extends T> boolean exists(Example<S> example, boolean ignoreSoftDeletion) {
        Assert.notNull(example, "Sample must not be null!");
        Query query = new Query(new Criteria().alike(example)) //
            .collation(entityInformation.getCollation());
        if (!ignoreSoftDeletion) {
            SoftDeletionUtils.queryWithNotDeleted(query, entityInformation.getJavaType());
        }
        return mongoOperations.exists(query, example.getProbeType(), entityInformation.getCollectionName());
    }

    private Query getIdQuery(Object id) {
        return getIdQuery(id, DEFAULT_SOFT_DELETION);
    }

    private Query getIdQuery(Object id, boolean ignoreSoftDeletion) {
        Query query = new Query(getIdCriteria(id));
        if (!ignoreSoftDeletion) {
            SoftDeletionUtils.queryWithNotDeleted(query, entityInformation.getJavaType());
        }
        return query;
    }

    private Criteria getIdCriteria(Object id) {
        return where(entityInformation.getIdAttribute()).is(id);
    }

    private Query getIdQuery(Iterable<? extends ID> ids) {
        return new Query(new Criteria(entityInformation.getIdAttribute()).in(toCollection(ids)));
    }

    private Query getIdQuery(Iterable<? extends ID> ids, boolean ignoreSoftDeletion) {
        Query query = new Query(new Criteria(entityInformation.getIdAttribute()).in(toCollection(ids)));
        if (!ignoreSoftDeletion) {
            SoftDeletionUtils.queryWithNotDeleted(query, entityInformation.getJavaType());
        }
        return query;
    }

    private List<T> findAll(@Nullable Query query) {
        return findAll(query, DEFAULT_SOFT_DELETION);
    }

    private List<T> findAll(@Nullable Query query, boolean ignoreSoftDeletion) {
        if (query == null) {
            return Collections.emptyList();
        }
        if (!ignoreSoftDeletion) {
            SoftDeletionUtils.queryWithNotDeleted(query, entityInformation.getJavaType());
        }
        return mongoOperations.find(query, entityInformation.getJavaType(), entityInformation.getCollectionName());
    }
}
