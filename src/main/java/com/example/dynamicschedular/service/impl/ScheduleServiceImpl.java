package com.example.dynamicschedular.service.impl;

import com.example.dynamicschedular.model.JobModel;
import com.example.dynamicschedular.model.response.ResponseEntity;
import com.example.dynamicschedular.model.ScheduledJob;
import com.example.dynamicschedular.service.DBQueryService;
import com.example.dynamicschedular.service.ScheduleService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final Logger LOG = Logger.getLogger(ScheduleServiceImpl.class);

    private final List<ScheduledJob> scheduledJobs = new ArrayList<>();

    private final DBQueryService dbQueryService;

    public ScheduleServiceImpl(DBQueryService dbQueryService) {
        this.dbQueryService = dbQueryService;
    }

    @Override
    public ResponseEntity<String> sayHello() {

        LOG.info("Endpoint /schedule/sayHello called...");
        return new ResponseEntity<>("Hello");

    }

    @Override
    public ResponseEntity<JobModel> createJob(JobModel job) {

        try {
            LOG.info("Endpoint /schedule/createJob called...");
            TaskScheduler taskScheduler = new ConcurrentTaskScheduler();
            job.setId(UUID.randomUUID());
            job.setEnabled(true);
            ScheduledFuture<?> scheduledTask = taskScheduler.schedule(() -> dbQueryService.sendQueryToDB(job), new CronTrigger(job.getCronExpression()));
            scheduledJobs.add(new ScheduledJob(job, scheduledTask));
            LOG.info(job.getId() + " job created!");
            return new ResponseEntity<>(job);
        } catch (Exception e) {
            LOG.error("Endpoint /schedule/createJob called and service threw an error. ErrorText: " + e.getLocalizedMessage());
            return new ResponseEntity<>("Endpoint /schedule/createJob called and service threw an error", 409);
        }

    }

    @Override
    public ResponseEntity<List<JobModel>> getJobs() {

        LOG.info("Endpoint /schedule/getJobs called...");
        List<JobModel> jobList = new ArrayList<>();
        scheduledJobs.forEach(scheduledJob -> jobList.add(scheduledJob.getJob()));
        LOG.info(scheduledJobs.size() + " jobs returned!");
        return new ResponseEntity<>(jobList);

    }

    @Override
    public ResponseEntity<String> pauseJob(UUID jobID) {

        try {
            LOG.info("Endpoint /schedule/pauseJob called...");
            for (ScheduledJob scheduledJob : scheduledJobs) {
                if (scheduledJob.getJob().getId().equals(jobID)) {
                    scheduledJob.getScheduledTask().cancel(true);
                    scheduledJob.getJob().setEnabled(false);
                    LOG.info(scheduledJob.getJob().getId() + " job paused!");
                    break;
                }
            }
            return new ResponseEntity<>(jobID + " job paused!");
        } catch (Exception e) {
            LOG.error("Endpoint /schedule/pauseJob called and service threw an error. ErrorText: " + e.getLocalizedMessage());
            return new ResponseEntity<>("Endpoint /schedule/pauseJob called and service threw an error", 409);
        }

    }

    @Override
    public ResponseEntity<String> restartJob(UUID jobID) {

        try {
            LOG.info("Endpoint /schedule/restartJob called...");
            for (ScheduledJob scheduledJob : scheduledJobs) {
                if (scheduledJob.getJob().getId().equals(jobID)) {
                    scheduledJob.getScheduledTask().cancel(true);
                    scheduledJob.getJob().setEnabled(false);

                    JobModel job = scheduledJob.getJob();
                    TaskScheduler taskScheduler = new ConcurrentTaskScheduler();
                    ScheduledFuture<?> scheduledTask = taskScheduler.schedule(() -> dbQueryService.sendQueryToDB(job), new CronTrigger(job.getCronExpression()));
                    job.setEnabled(true);
                    scheduledJob.setJob(job);
                    scheduledJob.setScheduledTask(scheduledTask);
                    LOG.info(scheduledJob.getJob().getId() + " job restarted!");
                    break;
                }
            }
            return new ResponseEntity<>(jobID + " job restarted!");
        } catch (Exception e) {
            LOG.error("Endpoint /schedule/restartJob called and service threw an error. ErrorText: " + e.getLocalizedMessage());
            return new ResponseEntity<>("Endpoint /schedule/restartJob called and service threw an error", 409);
        }

    }

    @Override
    public ResponseEntity<String> removeJob(UUID jobID) {

        try {
            LOG.info("Endpoint /schedule/removeJob called...");
            for (int i = 0; i < scheduledJobs.size(); i++) {
                if (scheduledJobs.get(i).getJob().getId().equals(jobID)) {
                    scheduledJobs.get(i).getScheduledTask().cancel(true);
                    scheduledJobs.remove(i);
                    LOG.info(scheduledJobs.get(i).getJob().getId() + " job removed!");
                    break;
                }
            }
            return new ResponseEntity<>(jobID + " job removed!");
        } catch (Exception e) {
            LOG.error("Endpoint /schedule/removeJob called and service threw an error. ErrorText: " + e.getLocalizedMessage());
            return new ResponseEntity<>("Endpoint /schedule/removeJob called and service threw an error", 409);
        }

    }

}
