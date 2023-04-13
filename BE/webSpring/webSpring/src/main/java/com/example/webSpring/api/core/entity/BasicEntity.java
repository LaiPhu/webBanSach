package com.example.webSpring.api.core.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class BasicEntity extends IdentifiedEntity implements Serializable {

    @Indexed
    @Field(name = "code")
    private String code;

    @Indexed
    @Field(name = "name")
    private String name;

    @Field(name = "description")
    private String description;
}
