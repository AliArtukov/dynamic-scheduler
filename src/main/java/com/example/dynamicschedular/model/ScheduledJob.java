package com.example.dynamicschedular.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ScheduledFuture;

@AllArgsConstructor
@Data
public class ScheduledJob {

    private JobModel job;

    private ScheduledFuture<?> scheduledTask;

}
