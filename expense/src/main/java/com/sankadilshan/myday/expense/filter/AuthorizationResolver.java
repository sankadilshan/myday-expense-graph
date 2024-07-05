package com.sankadilshan.myday.expense.filter;

import com.sankadilshan.myday.expense.dto.Authorization;
import com.sankadilshan.myday.expense.util.AuthorizationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthorizationResolver implements HandlerMethodArgumentResolver {

    private Validator validator;

    @Autowired
    public AuthorizationResolver(Validator validator) {
        this.validator= validator;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Authorization.class);
    }

    @Override
    public Object resolveArgument(MethodParameter param, ModelAndViewContainer mavContainer, NativeWebRequest request, WebDataBinderFactory binderFactory) throws Exception {
        String jwtToken = AuthorizationUtil.getJwtToken(request.getHeader("Authorization"));
        Authorization authorization = Authorization.builder().jwtToken(jwtToken).build();
        return authorization;
    }
}
