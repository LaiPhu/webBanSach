package com.example.webSpring.api.model.common;

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
@Document(collection = "images")
public class Image extends IdentifiedEntity implements Serializable {

    @Indexed
    private String fileName;

    @Indexed
    private String fileType;

    @Indexed
    @Field(targetType = FieldType.BINARY)
    private byte[] data;
}
