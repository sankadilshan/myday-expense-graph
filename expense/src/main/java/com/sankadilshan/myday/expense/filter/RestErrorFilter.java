package com.sankadilshan.myday.expense.filter;

import com.sankadilshan.myday.expense.exception.RestServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;


@Slf4j
public class RestErrorFilter {


    public static void errorFilter(Map response) {

        String status = String.valueOf(response.get("status"));
        if (status != null && status.contains("ERROR")) {
            throw new RestServiceException(String.valueOf(response.get("message")), status, Integer.parseInt(String.valueOf(response.get("code"))), String.valueOf(response.get("timestamp")));
        }
    }
}
