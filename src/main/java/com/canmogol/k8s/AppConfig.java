package com.canmogol.k8s;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Key value pairs configuration.
 */
@Configuration
@ConfigurationProperties(prefix = "app.config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppConfig {
    private Map<String, String> keyValuePairs;
}