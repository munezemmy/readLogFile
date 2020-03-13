package com.logs.readlogs.controller;
import com.logs.readlogs.model.LogEntry;
import com.logs.readlogs.service.LogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class LogEntryController {

    @Autowired
    private LogEntryService readFileService;

    @GetMapping ("/logs/visits/uri/**")
    public int getNumOfVisits(HttpServletRequest request) throws IOException {
        String uri=request.getRequestURL().toString().split("/logs/visits/uri/")[1];
        return readFileService.numberOfVisits(uri);
    }

    @GetMapping("/logs/visits/ip")
    public List<String> listOfIPvisted() throws IOException {
        return readFileService.listOfIPvisted();
    }

    @GetMapping("/logs/visits/ip/**")
    public int getNumOfVisitsPerIp(HttpServletRequest request) throws IOException{
        String ipAddress=request.getRequestURL().toString().split("/logs/visits/ip/")[1];
        return readFileService.numberOfVisitsPerIP(ipAddress);
    }
    @GetMapping("/logs/success")
    public  int getnumOfSuccessRequests() throws IOException{
        return readFileService.numOfSuccessRequests();
    }
    @GetMapping("/logs")
    public List<LogEntry> getAllLogEntries() throws IOException{
        return readFileService.allLogEntries();
    }
    @GetMapping("/logs/tmeouts")
    public int numOfTimeouts()throws IOException{
        return readFileService.numOfTimeOuts();
    }
    
}
