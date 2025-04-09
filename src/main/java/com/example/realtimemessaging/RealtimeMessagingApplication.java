package com.example.realtimemessaging;

import com.example.realtimemessaging.config.AppTokenConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppTokenConfig.class)
@SpringBootApplication
public class RealtimeMessagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealtimeMessagingApplication.class, args);
    }

}
