package com.sankadilshan.myday.expense.util;

import com.sankadilshan.myday.expense.dto.Authorization;
import com.sankadilshan.myday.expense.exception.AuthorizationFailedException;
import graphql.GraphQLContext;

import java.util.List;

public class AuthorizationUtil {

    public static String getJwtToken(String authHeader) {
        boolean isValidHeader = authHeader != null && authHeader.contains("BEARER");
        return isValidHeader ? authHeader.replace("BEARER", "") : null;
    }

    public static Authorization buildAuthorization(GraphQLContext context) {
        try {
            return Authorization.builder().jwtToken(((List<?>) context.get("Authorization")).get(0).toString()).build();
        } catch (Exception e) {
            throw  new AuthorizationFailedException();
        }
    }
}
