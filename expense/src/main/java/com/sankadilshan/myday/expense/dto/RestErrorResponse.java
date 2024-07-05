package com.sankadilshan.myday.expense.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestErrorResponse {
    private String status;
    private int code;
    private String message;
    private String timestamp;
}
