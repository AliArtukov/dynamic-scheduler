package com.example.dynamicschedular.service;

import com.example.dynamicschedular.model.JobModel;
import com.example.dynamicschedular.model.response.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ScheduleService {

    ResponseEntity<String> sayHello();

    ResponseEntity<JobModel> createJob(JobModel job);

    ResponseEntity<List<JobModel>> getJobs();

    ResponseEntity<String> pauseJob(UUID jobID);

    ResponseEntity<String> restartJob(UUID jobID);

    ResponseEntity<String> removeJob(UUID jobID);

}
