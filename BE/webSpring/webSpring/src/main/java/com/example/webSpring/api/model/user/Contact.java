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
@Document(collection = "contacts")
public class Contact extends IdentifiedEntity implements Serializable {

    @Indexed
    @Field(name = "name")
    private String name;

    @Indexed
    @Field(name = "phone_number")
    private Integer phoneNumber;

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
