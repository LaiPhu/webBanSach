
package com.example.webSpring.api.core.mongo;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SoftDeletionMongoRepository<T, ID> extends MongoRepository<T, ID> {
    Optional<T> findById(ID id, boolean ignoreSoftDeletion);

    boolean existsById(ID id, boolean ignoreSoftDeletion);

    List<T> findAll(boolean ignoreSoftDeletion);

    Iterable<T> findAllById(Iterable<ID> ids, boolean ignoreSoftDeletion);

    long count(boolean ignoreSoftDeletion);

    void deleteById(ID id, boolean ignoreSoftDeletion);

    void deleteAllById(Iterable<? extends ID> ids, boolean ignoreSoftDeletion);

    void deleteAll(boolean ignoreSoftDeletion);

    Page<T> findAll(Pageable pageable, boolean ignoreSoftDeletion);

    List<T> findAll(Sort sort, boolean ignoreSoftDeletion);

    <S extends T> Optional<S> findOne(Example<S> example, boolean ignoreSoftDeletion);

    <S extends T> List<S> findAll(Example<S> example, boolean ignoreSoftDeletion);

    <S extends T> List<S> findAll(Example<S> example, Sort sort, boolean ignoreSoftDeletion);

    <S extends T> Page<S> findAll(Example<S> example, Pageable pageable, boolean ignoreSoftDeletion);

    <S extends T> long count(Example<S> example, boolean ignoreSoftDeletion);

    <S extends T> boolean exists(Example<S> example, boolean ignoreSoftDeletion);
}
