
package com.example.webSpring.api.security.Api;

import com.example.webSpring.api.core.exception.ExceptionProblem;
import com.example.webSpring.api.core.exception.ExceptionType;
import lombok.Getter;
import org.zalando.problem.Status;

@Getter
public class SecureApiException extends ExceptionProblem {

    public SecureApiException(ExceptionType exceptionType) {
        super(exceptionType, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.FORBIDDEN);
    }

    public SecureApiException(String messageKey) {
        super(null, ExceptionType.TITLE_SECURE_API_EXCEPTION, Status.FORBIDDEN, messageKey);
    }
}
