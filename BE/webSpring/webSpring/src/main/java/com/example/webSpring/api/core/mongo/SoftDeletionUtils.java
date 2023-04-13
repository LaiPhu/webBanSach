
package com.example.webSpring.api.core.mongo;

import com.example.webSpring.api.core.Constants;
import com.example.webSpring.api.core.entity.SoftDeletionEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.Instant;

public class SoftDeletionUtils {

    private static final String SOFT_DELETION_KEY = Constants.SoftDeletion.KEY;

    public static Update delete() {
        return delete(new Update());
    }

    public static Update delete(Update update) {
        return update
            .set(SOFT_DELETION_KEY, true)
            .set(Constants.SoftDeletion.DELETED_DATE, Instant.now().toEpochMilli());
//            .set(Constants.SoftDeletion.DELETED_BY, SpringSecurityAuditorAware.getAuditor());
    }

    public static Criteria notDeleted() {
        return new Criteria()
            .orOperator(
                Criteria.where(SOFT_DELETION_KEY).exists(false),
                Criteria.where(SOFT_DELETION_KEY).is(null),
                Criteria.where(SOFT_DELETION_KEY).is(false)
            );
    }

    public static Query queryWithNotDeleted(Query query) {
        return query.addCriteria(notDeleted());
    }

    public static Query queryWithNotDeleted(Query query, Class clazz) {
        if (!SoftDeletionEntity.class.isAssignableFrom(clazz)) return query;
        return queryWithNotDeleted(query);
    }
}
