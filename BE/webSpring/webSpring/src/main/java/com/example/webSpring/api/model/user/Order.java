package com.example.webSpring.api.model.user;

import com.example.webSpring.api.core.entity.IdentifiedEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;

@Getter
@Setter
@Document(collection = "orders")
public class Order extends IdentifiedEntity implements Serializable {

    @Indexed
    @Field(targetType = FieldType.OBJECT_ID, name = "user_id")
    private String userId;

    @Indexed
    @Field(name = "order_date")
    private Long orderDate;

    @Indexed
    @Field(name = "delivery_date")
    private Long deliveryDate;

    @Field(name = "pay")
    private Integer pay;

    @Indexed
    @Field(name = "status")
    private Integer status;

    @Indexed
    @Field(name = "tracking")
    private Boolean tracking;
}
