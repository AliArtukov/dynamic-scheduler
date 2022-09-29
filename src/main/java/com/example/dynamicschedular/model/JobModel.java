package com.example.dynamicschedular.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobModel {

        private UUID id = UUID.randomUUID();

        @NonNull
        private String name;

        @NonNull
        private String queryToDB;

        @NonNull
        private String cronExpression;

        private boolean isEnabled = true;

        public void setEnabled(boolean isEnabled) {
                this.isEnabled = isEnabled;
        }

}
