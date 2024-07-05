package com.sankadilshan.myday.expense.exception;

import graphql.GraphQLException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GraphQLException.class)
    public ThrowableGraphQLError handle(GraphQLException e) {
        return new ThrowableGraphQLError(e, "Graphql Error");
    }

    @ExceptionHandler(RuntimeException.class)
    public ThrowableGraphQLError handle(RuntimeException e) {
        return new ThrowableGraphQLError(e,"Occurred runtime error");
    }

    @ExceptionHandler({AuthorizationFailedException.class})
    public ThrowableGraphQLError handle(AuthorizationFailedException e) {
        return new ThrowableGraphQLError(e,e.getMessage());
    }

    @ExceptionHandler({RestServiceException.class})
    public ThrowableGraphQLError handle(RestServiceException e) {
        return new ThrowableGraphQLError(e, e.getMessage());
    }
}
