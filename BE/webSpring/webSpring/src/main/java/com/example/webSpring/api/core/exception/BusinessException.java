package com.example.webSpring.api.core.exception;

import org.zalando.problem.Status;

public class BusinessException extends ExceptionProblem {

    public BusinessException(ExceptionType exceptionType) {
        super(exceptionType, ExceptionType.TITLE_BUSINESS_EXCEPTION, Status.BAD_REQUEST);
    }

    public BusinessException(String messageKey) {
        super(null, ExceptionType.TITLE_BUSINESS_EXCEPTION, Status.BAD_REQUEST, messageKey);
    }
}
