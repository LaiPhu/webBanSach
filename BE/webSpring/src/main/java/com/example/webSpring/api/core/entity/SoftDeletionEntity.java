package com.example.webSpring.api.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class SoftDeletionEntity implements Serializable {

    @JsonIgnore
    @Field(name = "deleted_date")
    private Long deletedDate;

    @JsonIgnore
    @Field(name = "deleted_by")
    private String deletedBy;

    @Indexed
    private Boolean deleted;
}
