package com.dev.java.bankingApplication.helper;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyDbHealthService implements HealthIndicator {
    public static final String DB_SERVICE = "Database Service";

    public boolean isHealthGood(){
        //custom logic to check any hardware or software running properly
        return true;
    }

    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }

    @Override
    public Health health() {
        if(isHealthGood()){
            return Health.up().withDetail(DB_SERVICE, "Database Service is running").build();//Creates Object for Health.
        }
        return Health.down().withDetail(DB_SERVICE, "Database Service is down").build();
    }
}
