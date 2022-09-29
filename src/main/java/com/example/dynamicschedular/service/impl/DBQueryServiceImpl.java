package com.example.dynamicschedular.service.impl;

import com.example.dynamicschedular.model.JobModel;
import com.example.dynamicschedular.service.DBQueryService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;

@Repository
public class DBQueryServiceImpl implements DBQueryService {

    private final Logger LOG = Logger.getLogger(DBQueryServiceImpl.class);

    @Override
    public void sendQueryToDB(JobModel job) {

        try {
            LOG.info(job.getName() + " start work at " + LocalTime.now());

            String dbURL = "jdbc:postgresql://localhost:5432/scheduler_db";
            String user = "postgres";
            String password = "Postgresql19971207.";

            Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement statement = conn.createStatement();
            boolean isResultSet = statement.execute(job.getQueryToDB());

            if (isResultSet) {
                ResultSet resultSet = statement.getResultSet();
                StringBuilder data = new StringBuilder();

                while (resultSet.next()) {
                    int index = 1;
                    while (true) {
                        try {
                            data.append(resultSet.getObject(index++)).append("\t");
                        } catch (Exception e) {
                            break;
                        }
                    }
                    data.append("\n");
                }
                LOG.info(job.getName() + " job get from DB data:\n" + data);
            }

        } catch (Exception e) {
            LOG.error(job.getName() + " query returned error. ErrorText: " + e.getLocalizedMessage());
        }

    }

}
