package com.example.dynamicschedular.model;

import lombok.*;

import java.util.UUID;

/*
Custom class with the necessary fields to create a schedule
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JobModel {

        private UUID id = UUID.randomUUID();

        @NonNull
        private String name;

        @NonNull
        private String queryToDB;

        @NonNull
        private String cronExpression;

        private boolean isEnabled = true;

        public void setId(UUID id) {
                this.id = id;
        }

        public void setEnabled(boolean isEnabled) {
                this.isEnabled = isEnabled;
        }

}
