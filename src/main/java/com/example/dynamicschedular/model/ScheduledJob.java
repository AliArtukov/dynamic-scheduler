package com.example.dynamicschedular.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ScheduledFuture;

/*
Custom class for saving task data and the schedule itself
 */
@AllArgsConstructor
@Data
public class ScheduledJob {

    private JobModel job;

    private ScheduledFuture<?> scheduledTask;

}
