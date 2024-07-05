package com.sankadilshan.myday.expense.service.resolvers;

import com.sankadilshan.myday.expense.service.serviceImpl.GraphHealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class HealthResolver {

    private final GraphHealthIndicator healthIndicator;

    @Autowired
    public HealthResolver(GraphHealthIndicator healthIndicator){
        this.healthIndicator= healthIndicator;
    }

    public Health getHealth() {
      return healthIndicator.health();
    }

}
