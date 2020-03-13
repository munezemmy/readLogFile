package com.logs.readlogs.service;
import com.logs.readlogs.helperClass.ReadFile;
import com.logs.readlogs.model.LogEntry;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogEntryService {

    private ReadFile rf=new ReadFile();

    public int numOfSuccessRequests() throws IOException{

        return Math.toIntExact(
                rf.readMyLogFile().map(a -> rf.generateLogEntryObject(a))
                        .filter(s -> s.getStatusCode().equals("200"))
                        .count());
    }



    public int numOfTimeOuts() throws IOException{
        return Math.toIntExact(
                rf.readMyLogFile().map(a -> rf.generateLogEntryObject(a))
                        .filter(a -> a.getStatusCode().equals("408")).count());
    }

    public int numberOfVisits(String uri) throws IOException{
        return Math.toIntExact(
               rf.readMyLogFile().map(a ->rf.generateLogEntryObject(a))
                        .filter(a->a.getRequestURI()!=null)
                        .filter(a ->a.getRequestURI().equals(uri)).count());
    }


    public List<String> listOfIPvisted() throws IOException {
        return rf.readMyLogFile().map(a->rf.generateLogEntryObject(a))
                .filter(a->a.getMethodOfRequest()!=null)
                .filter(a->!a.getMethodOfRequest().equals("-"))
                .map(a->a.getIpAddress()).distinct().collect(Collectors.toList());
    }

    public int numberOfVisitsPerIP(String ip)throws IOException{
        return Math.toIntExact(
                rf.readMyLogFile().map(a->rf.generateLogEntryObject(a))
                .filter(a->a.getIpAddress()!=null)
                .filter(a->a.getMethodOfRequest()!=null)
                .filter(a->!a.getMethodOfRequest().equals("-"))
                .filter(a->a.getIpAddress().equals(ip))
                .count());
    }

    public List<LogEntry> allLogEntries()throws IOException{
        return rf.readMyLogFile().map(a->rf.generateLogEntryObject(a))
                .filter(a->a.getMethodOfRequest()!=null).collect(Collectors.toList());
    }

}
