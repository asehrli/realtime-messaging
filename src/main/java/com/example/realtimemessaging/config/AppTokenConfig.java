package com.example.realtimemessaging.config;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "app.token")
public class AppTokenConfig {
    String accessSecretKey;
    long accessExpiration;
    String refreshSecretKey;
    long refreshExpiration;
}
