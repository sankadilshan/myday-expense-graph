package com.sankadilshan.myday.expense.dto;

import graphql.ErrorType;
import graphql.kickstart.execution.error.GenericGraphQLError;

public class CustomGraphQLError extends GenericGraphQLError {

    private ErrorType errorType;
    private Object detailMessage;

    private String status;
    private int code;
    private String timestamp;

    public CustomGraphQLError(String message, ErrorType errorType, Object detailMessage) {
        super(message);
        this.errorType= errorType;
        this.detailMessage= detailMessage;
    }

    public CustomGraphQLError(String message, ErrorType errorType, String status, int code,String timestamp) {
        super(message);
        this.errorType= errorType;
        this.status= status;
        this.code= code;
        this.timestamp= timestamp;
    }

    public CustomGraphQLError(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return this.errorType;
    }

    public Object getDetailMessage() {
        return this.detailMessage;
    }
}
