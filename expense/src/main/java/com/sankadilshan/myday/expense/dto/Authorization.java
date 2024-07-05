package com.sankadilshan.myday.expense.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Authorization {

    private String jwtToken;
}
