package com.example.webSpring.api.core.exception;

import com.example.webSpring.api.core.Constants;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;
@Getter
public enum ExceptionType {

    NOT_FOUND_USER("validation.notFoundUser", null),
    EXISTS_USER("validation.existsUser", null),
    SORT_ORDER_MAX("validation.sortOrderMax", null),
    HAVE_CHILDREN_TENANT("validation.containChildTenant", null),
    EXISTS_PHONE_OR_EMAIL("validation.existsPhoneNumber", null),
    NOT_FOUND_BOOK("validation.notFoundBook", null);

    public static final String TITLE_BUSINESS_EXCEPTION = "problem.title.exception.business";
    public static final String TITLE_SECURE_API_EXCEPTION = "problem.title.exception.secureApi";
    public static final String TITLE_METHOD_ARGUMENT_NOT_VALID = "problem.title.methodArgumentNotValid";

    public static final String PROBLEM_BASE_URL = Constants.BASE_WEB_URL + "/technical-problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/business-exception");
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");

    private final String messageKey;
    private final String problemUrl;
    private URI problemUri;

    ExceptionType(String messageKey, String problemUrl) {
        this.messageKey = messageKey;
        this.problemUrl = StringUtils.defaultString(problemUrl);
        if (!StringUtils.isBlank(problemUrl)) this.problemUri = URI.create(PROBLEM_BASE_URL + problemUrl);
    }
}
