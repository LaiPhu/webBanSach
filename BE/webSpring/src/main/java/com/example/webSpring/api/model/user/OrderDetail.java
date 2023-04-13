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
@Document(collection = "orderDetails")
public class OrderDetail extends IdentifiedEntity implements Serializable {

    @Indexed
    @Field(targetType = FieldType.OBJECT_ID, name = "book_id")
    private String bookId;

    @Indexed
    @Field(targetType = FieldType.OBJECT_ID, name = "order_id")
    private String orderId;

    @Field(name = "price")
    private Double price;

    @Indexed
    @Field(name = "number")
    private Integer number;
}
