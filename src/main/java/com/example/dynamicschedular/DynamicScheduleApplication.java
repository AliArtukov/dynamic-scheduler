package com.example.dynamicschedular;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;
import java.time.ZoneId;

@SpringBootApplication
public class DynamicScheduleApplication {

    private static final Logger LOG = Logger.getLogger(DynamicScheduleApplication.class);

    public static void main(String[] args) {
        LOG.info("Dynamic Scheduler started at " + LocalTime.now(ZoneId.of("Asia/Tashkent")));
        SpringApplication.run(DynamicScheduleApplication.class, args);
    }

}
