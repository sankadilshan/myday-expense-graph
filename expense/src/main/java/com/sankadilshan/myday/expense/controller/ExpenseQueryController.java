package com.sankadilshan.myday.expense.controller;

import com.sankadilshan.myday.expense.dto.CustomGraphQLError;
import com.sankadilshan.myday.expense.exception.RestServiceException;
import com.sankadilshan.myday.expense.service.resolvers.ExpenseResolver;
import com.sankadilshan.myday.expense.service.resolvers.HealthResolver;
import com.sankadilshan.myday.expense.util.GraphqlUtilResponse;
import graphql.ErrorType;
import graphql.GraphQLContext;
import graphql.execution.DataFetcherResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Map;

import static graphql.execution.DataFetcherResult.newResult;

@Slf4j
@Controller
public class ExpenseQueryController {

    private final HealthResolver healthResolver;
    private final ExpenseResolver expenseResolver;

    @Autowired
    public ExpenseQueryController(HealthResolver resolver, ExpenseResolver expenseResolver) {
        this.healthResolver = resolver;
        this.expenseResolver = expenseResolver;
    }

    @QueryMapping(name = "getHealth")
    public Health checkHealth() {
        return healthResolver.getHealth();
    }

    @QueryMapping("getAllExpenses")
    public DataFetcherResult<Object> getAllExpenses(GraphQLContext context) {
        try {
            var obj = expenseResolver.getAllExpenses(context);
            return DataFetcherResult
                    .newResult()
                    .data(GraphqlUtilResponse
                            .generateGraphqlResponse(obj))
                    .build();
        } catch (RestServiceException e) {
            return newResult().data(GraphqlUtilResponse.generateGraphqlResponse(new ArrayList<>()))
                    .error(new CustomGraphQLError(e.getMessage(),ErrorType.ExecutionAborted,e.getStatus(),e.getCode(),e.getTimestamp())).build();
        }
        catch (Exception e) {
            return newResult().data(GraphqlUtilResponse.generateGraphqlResponse(new ArrayList<>()))
                    .error(new CustomGraphQLError(e.getMessage(),ErrorType.ExecutionAborted, GraphqlUtilResponse.convertStringToJson(e.getMessage()))).build();
        }
    }

    @QueryMapping
    public DataFetcherResult<Object> getExpensesByUserId(@Argument Long userId, GraphQLContext context) throws RuntimeException {
        try {
            var obj = expenseResolver.getExpensesByUserId(userId, context);
            return DataFetcherResult
                    .newResult()
                    .data(GraphqlUtilResponse
                            .generateGraphqlResponse(obj))
                    .build();
        } catch ( Exception e) {
            return newResult().data(GraphqlUtilResponse.generateGraphqlResponse(new ArrayList<>()))
                    .error(new CustomGraphQLError(e.getMessage(),ErrorType.ExecutionAborted, GraphqlUtilResponse.convertStringToJson(e.getMessage()))).build();
        }
    }

    @MutationMapping("insertExpense")
    public Object insertExpense(@Argument Map<String, Object> expense, GraphQLContext context) {
        Object obj = expenseResolver.insertExpense(expense, context);
        return GraphqlUtilResponse.generateGraphqlResponse(obj);
    }

}
