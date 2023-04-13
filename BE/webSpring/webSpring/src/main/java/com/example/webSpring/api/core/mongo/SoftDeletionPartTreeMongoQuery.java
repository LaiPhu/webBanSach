
package com.example.webSpring.api.core.mongo;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.ConvertingParameterAccessor;
import org.springframework.data.mongodb.repository.query.PartTreeMongoQuery;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;
import org.springframework.expression.ExpressionParser;

public class SoftDeletionPartTreeMongoQuery extends PartTreeMongoQuery {

    SoftDeletionPartTreeMongoQuery(
        PartTreeMongoQuery partTreeQuery,
        MongoOperations mongoOperations,
        ExpressionParser expressionParser,
        QueryMethodEvaluationContextProvider evaluationContextProvider
    ) {
        super(partTreeQuery.getQueryMethod(), mongoOperations, expressionParser, evaluationContextProvider);
    }

    @Override
    protected Query createQuery(ConvertingParameterAccessor accessor) {
        Query query = super.createQuery(accessor);
        return SoftDeletionUtils.queryWithNotDeleted(query);
    }

    @Override
    protected Query createCountQuery(ConvertingParameterAccessor accessor) {
        Query query = super.createCountQuery(accessor);
        return SoftDeletionUtils.queryWithNotDeleted(query);
    }
}
