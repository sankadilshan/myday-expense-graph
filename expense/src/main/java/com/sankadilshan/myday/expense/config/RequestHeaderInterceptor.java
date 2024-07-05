package com.sankadilshan.myday.expense.config;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class RequestHeaderInterceptor implements WebGraphQlInterceptor {

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        HttpHeaders headers = request.getHeaders();
        Map<String, Object> context = generateExpenseContext(headers);
        request.configureExecutionInput(((executionInput, builder) ->
                builder.graphQLContext(context).build()));
        return chain.next(request);
    }

    private Map<String,Object> generateExpenseContext(HttpHeaders headers) {
        Map<String, Object> expenseContext = new HashMap<>();
        expenseContext.put("Authorization", headers.get("Authorization") != null ? headers.get("Authorization"): "");
        return expenseContext;
    }
}
