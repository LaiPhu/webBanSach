package com.example.webSpring.api.repository.user;

import com.example.webSpring.api.model.user.OrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OrderDetailRepositoryCustomImpl implements OrderDetailRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    @Override
    public List<OrderDetail> findTop10BooksByNumber(Integer num) {
        AggregationOperation group = Aggregation.group("bookId")
                .sum("number").as("totalNumber");

        AggregationOperation match = Aggregation.match(Criteria.where("totalNumber").gte(0));

        AggregationOperation sort = Aggregation.sort(Sort.by(Sort.Direction.DESC, "totalNumber"));

        AggregationOperation limit = Aggregation.limit(num);

        TypedAggregation<OrderDetail> aggregation = Aggregation.newAggregation(OrderDetail.class, group, match, sort, limit);

        return mongoTemplate.aggregate(aggregation, OrderDetail.class).getMappedResults();
    }
}
