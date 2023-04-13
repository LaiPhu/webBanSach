
package com.example.webSpring.api.core.mongo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.PartTreeMongoQuery;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;

@RequiredArgsConstructor
public class SoftDeletionMongoQueryLookupStrategy implements QueryLookupStrategy {

    private static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();
    private final QueryLookupStrategy strategy;
    private final MongoOperations operations;
    private final QueryMethodEvaluationContextProvider evaluationContextProvider;

    @Override
    public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory, NamedQueries namedQueries) {
        RepositoryQuery repositoryQuery = strategy.resolveQuery(method, metadata, factory, namedQueries);
        if (method.getAnnotation(IgnoreSoftDeletion.class) != null) {
            return repositoryQuery;
        }
        if (!(repositoryQuery instanceof PartTreeMongoQuery)) {
            return repositoryQuery;
        }
        PartTreeMongoQuery partTreeQuery = (PartTreeMongoQuery) repositoryQuery;
        return new SoftDeletionPartTreeMongoQuery(partTreeQuery, operations, EXPRESSION_PARSER, evaluationContextProvider);
    }
}
