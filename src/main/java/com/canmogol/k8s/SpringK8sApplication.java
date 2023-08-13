package com.canmogol.k8s;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@Slf4j
public class SpringK8sApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringK8sApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {

        final Random random = new Random();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    log.info("Spring Boot K8s random, date: " + new Date());
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
                try {
                    int i = random.nextInt(2);
                    if (i == 1) {
                        throw new Exception("Random Exception with date: " + new Date() + " i: " + i);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }).start();
        return args -> log.info("Application started");
    }
}
