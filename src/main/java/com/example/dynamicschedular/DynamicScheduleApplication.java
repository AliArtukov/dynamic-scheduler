package com.example.dynamicschedular;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynamicScheduleApplication {

    private static final Logger LOG = Logger.getLogger(DynamicScheduleApplication.class);

    public static void main(String[] args) {
        LOG.info("Dynamic Scheduler started...");
        SpringApplication.run(DynamicScheduleApplication.class, args);
    }

}
