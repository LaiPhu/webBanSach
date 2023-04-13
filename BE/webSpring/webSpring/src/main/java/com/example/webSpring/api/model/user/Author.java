package com.example.webSpring.api.model.user;

import com.example.webSpring.api.core.entity.IdentifiedEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
@Getter
@Setter
@Document(collection = "authors")
public class Author extends IdentifiedEntity implements Serializable {

    @Indexed
    @Field(name = "name")
    private String name;

    @Field(name = "hometown")
    private String hometown;

    @Field(name = "date")
    private Long date;

    @Indexed
    @Field(name = "story")
    private String story;
}