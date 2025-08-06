package com.bridgelabz.greeting.controller;

import com.bridgelabz.greeting.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/data")
public class DataSeederController {
    
    @Autowired
    private AuditLogService auditLogService;
    
    @PostMapping("/seed-audit-logs")
    public String seedAuditLogs() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            // Create some sample audit logs for qa_manual1
            auditLogService.logOperation("qa_manual1", "LOGIN", "User login successful", "192.168.1.100");
            Thread.sleep(1000);
            
            auditLogService.logOperation("qa_manual1", "CREATE_GREETING", "Created greeting for John Doe", "192.168.1.100");
            Thread.sleep(1000);
            
            auditLogService.logOperation("qa_manual1", "UPDATE_GREETING", "Updated greeting with ID: 1 for Jane Smith", "192.168.1.100");
            Thread.sleep(1000);
            
            auditLogService.logOperation("qa_manual1", "VIEW_GREETING", "Viewed greeting with ID: 1", "192.168.1.100");
            Thread.sleep(1000);
            
            auditLogService.logOperation("qa_manual1", "DELETE_GREETING", "Deleted greeting with ID: 2", "192.168.1.100");
            Thread.sleep(1000);
            
            auditLogService.logOperation("qa_manual1", "EXPORT_DATA", "Exported user data", "192.168.1.100");
            Thread.sleep(1000);
            
            auditLogService.logOperation("qa_manual1", "LOGOUT", "User logout successful", "192.168.1.100");
            
            // Add some logs for other clients too
            auditLogService.logOperation("qa_auto2", "LOGIN", "Automated test login", "192.168.1.200");
            auditLogService.logOperation("prod_client3", "CREATE_GREETING", "Production greeting created", "10.0.0.50");
            
            return "Sample audit logs created successfully for qa_manual1 and other clients!";
            
        } catch (InterruptedException e) {
            return "Error creating sample data: " + e.getMessage();
        }
    }
}