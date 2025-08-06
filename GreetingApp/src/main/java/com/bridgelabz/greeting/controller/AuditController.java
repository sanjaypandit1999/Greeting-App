package com.bridgelabz.greeting.controller;

import com.bridgelabz.greeting.model.AuditLog;
import com.bridgelabz.greeting.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/audit")
public class AuditController {
    
    @Autowired
    private AuditLogService auditLogService;
    
    @GetMapping("/client/{clientName}")
    public List<AuditLog> getAuditLogsByClient(@PathVariable String clientName) {
        return auditLogService.getAuditLogsByClient(clientName);
    }
    
    @GetMapping("/client/{clientName}/summary")
    public String getAuditLogSummary(@PathVariable String clientName) {
        return auditLogService.getAuditLogSummary(clientName);
    }
    
    @GetMapping("/client/{clientName}/date")
    public List<AuditLog> getAuditLogsByClientAndDate(
            @PathVariable String clientName,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return auditLogService.getAuditLogsByClientAndDate(clientName, date);
    }
    
    @GetMapping("/client/{clientName}/daterange")
    public List<AuditLog> getAuditLogsByClientAndDateRange(
            @PathVariable String clientName,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return auditLogService.getAuditLogsByClientAndDateRange(clientName, startDate, endDate);
    }
    
    @PostMapping("/log")
    public String logOperation(
            @RequestParam String clientName,
            @RequestParam String operation,
            @RequestParam String details,
            HttpServletRequest request) {
        String ipAddress = getClientIpAddress(request);
        auditLogService.logOperation(clientName, operation, details, ipAddress);
        return "Audit log entry created successfully";
    }
    
    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader == null) {
            return request.getRemoteAddr();
        } else {
            return xForwardedForHeader.split(",")[0];
        }
    }
}