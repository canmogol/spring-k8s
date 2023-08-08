package com.canmogol.k8s;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Key value pairs Controller.
 */
@RestController
@AllArgsConstructor
@Slf4j
public class KeyValuePairsController {

    private final AppConfig config;

    @GetMapping("/")
    public Map<String, String> getPairs() {
        return config.getKeyValuePairs();
    }

}