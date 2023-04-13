
package com.example.webSpring.api.core.mongo;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;

import java.util.Optional;

public class SoftDeletionMongoRepositoryFactory extends MongoRepositoryFactory {

    private final MongoOperations mongoOperations;

    public SoftDeletionMongoRepositoryFactory(MongoOperations mongoOperations) {
        super(mongoOperations);
        this.mongoOperations = mongoOperations;
    }

    @Override
    protected Optional<QueryLookupStrategy> getQueryLookupStrategy(
        QueryLookupStrategy.Key key,
        QueryMethodEvaluationContextProvider evaluationContextProvider
    ) {
        Optional<QueryLookupStrategy> optStrategy = super.getQueryLookupStrategy(key, evaluationContextProvider);
        return Optional.of(new SoftDeletionMongoQueryLookupStrategy(optStrategy.get(), mongoOperations, evaluationContextProvider));
    }
}
