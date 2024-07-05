package com.sankadilshan.myday.expense.service.resolvers;

import com.sankadilshan.myday.expense.filter.RestErrorFilter;
import com.sankadilshan.myday.expense.util.AuthorizationUtil;
import com.sankadilshan.myday.expense.util.RestCommunication;
import graphql.GraphQLContext;
import graphql.GraphQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class ExpenseResolver {

    private final RestCommunication restCommunication;
    @Value("${myday.service.url}")
    private String SERVICE_BASE_URL;
    @Value("${myday.service.expense.path}")
    private String EXPENSE_PATH_URL;

    @Autowired
    public ExpenseResolver(RestCommunication restCommunication) {
        this.restCommunication = restCommunication;
    }

    public ArrayList getAllExpenses(GraphQLContext context) throws GraphQLException, RuntimeException {
        log.info("get all expenses :: Expense Resolver");
        String url = SERVICE_BASE_URL + EXPENSE_PATH_URL;
        ResponseEntity<?> response = restCommunication.doRequest(AuthorizationUtil.buildAuthorization(context), url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        log.info("response {}", response);
        RestErrorFilter.errorFilter((Map) Objects.requireNonNull(response.getBody()));
        return (ArrayList) ((Map<?, ?>) Objects.requireNonNull(response.getBody())).get("data");
    }

    public ArrayList getExpensesByUserId(Long userId, GraphQLContext context) throws RuntimeException {
        log.info("get all expenses :: Expense Resolver");
        try {
            String url = SERVICE_BASE_URL + EXPENSE_PATH_URL + userId;
            ResponseEntity<?> response = restCommunication.doRequest(AuthorizationUtil.buildAuthorization(context), url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
            });
            return (ArrayList) ((Map<?, ?>) Objects.requireNonNull(response.getBody())).get("data");
        } catch (GraphQLException e) {
            throw new GraphQLException(e);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public Object insertExpense(Map<String, Object> expense, GraphQLContext context) {
        log.info("insert an expense :: Expense Resolver");
        String url = SERVICE_BASE_URL + EXPENSE_PATH_URL;
        ResponseEntity<?> response = restCommunication.doRequest(AuthorizationUtil.buildAuthorization(context), url, HttpMethod.POST, expense, new ParameterizedTypeReference<Object>() {
        });
        return ((Map<?, ?>) Objects.requireNonNull(response.getBody())).get("data");
    }
}
