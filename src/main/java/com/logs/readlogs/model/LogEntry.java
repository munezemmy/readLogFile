package com.logs.readlogs.model;

import lombok.Data;

@Data
public class LogEntry {
    private String ipAddress;
    private String date;
    private String requestedAPI;
    private String statusCode;
    private String methodOfRequest;
    private String requestURI;
}
