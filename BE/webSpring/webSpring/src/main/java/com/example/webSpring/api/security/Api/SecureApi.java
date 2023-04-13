
package com.example.webSpring.api.security.Api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SecureApi {
    boolean authorized() default true;

    boolean encrypted() default false;

    ApiPermissions permissions() default @ApiPermissions;
}
