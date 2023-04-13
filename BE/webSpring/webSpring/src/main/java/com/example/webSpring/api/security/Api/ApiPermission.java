
package com.example.webSpring.api.security.Api;

import com.example.webSpring.api.core.security.OperationType;
import com.example.webSpring.api.core.security.ResourceType;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, TYPE, ANNOTATION_TYPE})
@Repeatable(ApiPermissions.class)
public @interface ApiPermission {
    ResourceType resource();

    OperationType[] operations();
}
