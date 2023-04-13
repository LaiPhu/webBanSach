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
@Document(collection = "books")
public class Book extends IdentifiedEntity implements Serializable {

    @Indexed
    @Field(targetType = FieldType.OBJECT_ID, name = "author_id")
    private String authorId;

    @Indexed
    @Field(targetType = FieldType.OBJECT_ID, name = "category_id")
    private String categoryId;

    @Indexed
    @Field(targetType = FieldType.OBJECT_ID, name = "publishingCompany_id")
    private String publishingCompanyId;

    @Indexed
    @Field(name = "name")
    private String name;

    @Field(name = "price")
    private Double price;

    @Field(name = "description")
    private String description;

    @Indexed
    @Field(name = "translator")
    private String translator;

    @Indexed
    @Field(name = "quantity_stock")
    private Integer quantityStock;

    @Field(name = "image")
    private String image;
}
