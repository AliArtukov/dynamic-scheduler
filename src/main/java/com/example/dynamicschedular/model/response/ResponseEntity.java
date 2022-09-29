package com.example.dynamicschedular.model.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class ResponseEntity<T> {

    private T responseBody;

    private final Integer statusCode;

    private String errorMessage;

    private final LocalDateTime dateTime;

    public ResponseEntity(T responseBody) {
        this.responseBody = responseBody;
        this.statusCode = 200;
        this.dateTime = ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime();
    }

    public ResponseEntity(String errorMessage, Integer statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
        this.dateTime = ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime();
    }

}