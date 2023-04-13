package com.example.webSpring.api.model.user;

import com.example.webSpring.api.core.entity.IdentifiedEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;

@Getter
@Setter
@Document(collection = "users")
public class User extends IdentifiedEntity implements Serializable {

    @Indexed
    @Field(name = "name")
    private String name;

    @Field(name = "address")
    private String address;

    @Indexed
    @Field(name = "phone_number")
    private String phoneNumber;

    @Indexed
    @Field(name = "date")
    private Long date;

    @Field(name = "pass_word")
    private String passWord;

    @Indexed
    @Field(name = "status")
    private Integer status;

    @Indexed
    @Field(name = "permission")
    private Integer permission;
}
