package com.logs.readlogs.helperClass;

import com.logs.readlogs.model.LogEntry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadFile {


    public LogEntry generateLogEntryObject(String line) {

        String[] lineColums = line.split("[\\s\\[\"]+");
        LogEntry myLogFile = new LogEntry();
        myLogFile.setIpAddress(lineColums[0]);
        myLogFile.setDate(lineColums[3]);
        if (lineColums[5].equals("-")) {
            myLogFile.setRequestedAPI("-");
            myLogFile.setStatusCode(lineColums[6]);
            return myLogFile;
        } else {
            String[] checkURI = lineColums[6].split("[?]");
            myLogFile.setRequestedAPI(lineColums[5] + " " + lineColums[6] + " " + lineColums[7]);
            myLogFile.setStatusCode(lineColums[8]);
            myLogFile.setMethodOfRequest(lineColums[5]);
            myLogFile.setRequestURI(checkURI[0]);
        }
        return myLogFile;
    }

    public Stream<String> readMyLogFile() throws IOException {
        Path filePath = Paths.get("/Users/emmy/Documents/TechMileage /practice project/readlogs/", "access_log");
        Stream<String> lines = Files.lines(filePath);
        return lines;
    }


}
