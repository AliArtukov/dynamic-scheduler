package com.example.dynamicschedular.service;

import com.example.dynamicschedular.model.JobModel;

public interface DBQueryService {

    void sendQueryToDB(JobModel job);

}
