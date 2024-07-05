package com.sankadilshan.myday.expense.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class RestServiceException extends RuntimeException{

    private String status;
    private int code;
    private String timestamp;
    public RestServiceException(String message, String status, int code, String timestamp){
        super(message);
        this.status= status;
        this.code= code;
        this.timestamp= timestamp;
    }
}
