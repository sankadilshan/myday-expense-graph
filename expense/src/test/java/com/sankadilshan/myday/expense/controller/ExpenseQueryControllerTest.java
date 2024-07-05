package com.sankadilshan.myday.expense.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(ExpenseQueryController.class)
public class ExpenseQueryControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;


    @Test
    void shouldGetAllExpenses() {
        this.graphQlTester.document("getAllExpenses")
                .execute()
                .path("getAllExpenses")
                .matchesJson("{}");
    }
}
