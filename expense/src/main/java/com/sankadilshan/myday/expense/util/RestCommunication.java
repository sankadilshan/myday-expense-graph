package com.sankadilshan.myday.expense.util;

import com.sankadilshan.myday.expense.dto.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Slf4j
@Component
public class RestCommunication {

    private RestTemplate restTemplate;

    @Autowired
    public RestCommunication(RestTemplate template){
        this.restTemplate = template;
    }

    public <T> ResponseEntity<T> doRequest(Authorization authorization, String url, HttpMethod httpMethod, Object body, ParameterizedTypeReference<T> className ){
        try {
            return restTemplate.exchange(new RequestEntity<>(body, generateHeaders(authorization), httpMethod, URI.create(url)), className);
        } catch (Exception e) {
            log.error("error occurred when sending request {}", e.getMessage(),e);
            throw e;
        }
    }

    private HttpHeaders generateHeaders(Authorization authorization){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization.getJwtToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }
}
