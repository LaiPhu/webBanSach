package com.example.webSpring.api.core.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class HierarchyEntity extends BasicEntity implements Serializable {

    @Indexed
    @Field(targetType = FieldType.OBJECT_ID, name = "parent_id")
    private String parentId;

    @Indexed
    @Field(name = "sort_order")
    private Integer sortOrder;

    @Indexed
    private Integer level;

    @Field(name = "children_count")
    private Integer childrenCount;

    @Field(name = "first_level_children_count")
    private Integer firstLevelChildrenCount;

    @Indexed
    @Field(name = "path_id")
    private String pathId;

    @Field(name = "path_code")
    private String pathCode;

    @Field(name = "path_name")
    private String pathName;

    @Indexed
    @Field(name = "path_order")
    private String pathOrder;

    @ReadOnlyProperty
    @Setter(AccessLevel.NONE)
    private Boolean hasChild;

    public Boolean getHasChild() {
        return childrenCount != null && childrenCount > 0;
    }
}
