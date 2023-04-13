package com.example.webSpring.api.model.admin;

import com.example.webSpring.api.core.entity.IdentifiedEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Getter
@Setter
@Document(collection = "publishingCompanies")
public class AdminPublishingCompany extends IdentifiedEntity implements Serializable {

    @Indexed
    @Field(name = "name")
    private String name;

    @Field(name = "address")
    private String address;

    @Field(name = "phone_number")
    private Integer phoneNumber;
}