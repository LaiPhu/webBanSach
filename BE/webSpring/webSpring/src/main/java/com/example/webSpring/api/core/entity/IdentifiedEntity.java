package com.example.webSpring.api.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class IdentifiedEntity extends AuditingEntity {

    @JsonIgnore
    @ReadOnlyProperty
    @Setter(AccessLevel.NONE)
    protected boolean compareById = true;

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Override
    public boolean equals(Object o) {
        if (!isCompareById()) return super.equals(o);
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentifiedEntity that = (IdentifiedEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        if (!isCompareById()) return super.hashCode();
        return Objects.hash(getId());
    }
}
