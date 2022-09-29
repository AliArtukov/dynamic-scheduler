package com.example.dynamicschedular.controller;

import com.example.dynamicschedular.model.JobModel;
import com.example.dynamicschedular.model.response.ResponseEntity;
import com.example.dynamicschedular.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/sayHello")
    public ResponseEntity<String> sayHello() {

        return scheduleService.sayHello();

    }

    @PostMapping(path = "/createJob")
    public ResponseEntity<JobModel> createJob(@RequestBody JobModel job) {

        return scheduleService.createJob(job);

    }

    @GetMapping(path = "/getJobs")
    public ResponseEntity<List<JobModel>> getJobs() {

        return scheduleService.getJobs();

    }

    @GetMapping(path = "/pauseJob/{jobID}")
    public ResponseEntity<String> pauseJob(@PathVariable UUID jobID) {

        return scheduleService.pauseJob(jobID);

    }

    @GetMapping(path = "/restartJob/{jobID}")
    public ResponseEntity<String> restartJob(@PathVariable UUID jobID) {

        return scheduleService.restartJob(jobID);

    }

    @DeleteMapping(path = "/removeJob/{jobID}")
    public ResponseEntity<String> removeJob(@PathVariable UUID jobID) {

        return scheduleService.removeJob(jobID);

    }

}
