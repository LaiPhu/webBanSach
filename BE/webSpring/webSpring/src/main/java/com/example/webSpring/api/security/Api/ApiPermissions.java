
package com.example.webSpring.api.security.Api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, TYPE, ANNOTATION_TYPE})
public @interface ApiPermissions {
    ApiPermission[] value() default {};
}
