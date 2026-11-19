package com.example.springbootdemo.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationScheduling {
    @Scheduled(fixedRate = 3000)
    public void scheduleTask() {
        log.info("System running");
    }
}
