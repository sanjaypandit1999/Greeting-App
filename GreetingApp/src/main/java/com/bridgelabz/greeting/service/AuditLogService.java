package com.bridgelabz.greeting.service;

import com.bridgelabz.greeting.model.AuditLog;
import com.bridgelabz.greeting.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AuditLogService {
    
    @Autowired
    private AuditLogRepository auditLogRepository;
    
    public void logOperation(String clientName, String operation, String details, String ipAddress) {
        AuditLog auditLog = new AuditLog(clientName, operation, details, ipAddress);
        auditLogRepository.save(auditLog);
    }
    
    public List<AuditLog> getAuditLogsByClient(String clientName) {
        return auditLogRepository.findByClientNameOrderByTimestampDesc(clientName);
    }
    
    public List<AuditLog> getAuditLogsByClientAndDate(String clientName, Date date) {
        return auditLogRepository.findByClientNameAndDate(clientName, date);
    }
    
    public List<AuditLog> getAuditLogsByClientAndDateRange(String clientName, Date startDate, Date endDate) {
        return auditLogRepository.findByClientNameAndTimestampBetweenOrderByTimestampDesc(clientName, startDate, endDate);
    }
    
    public String getAuditLogSummary(String clientName) {
        List<AuditLog> logs = getAuditLogsByClient(clientName);
        if (logs.isEmpty()) {
            return "No audit logs found for client: " + clientName;
        }
        
        StringBuilder summary = new StringBuilder();
        summary.append("=== AUDIT LOG SUMMARY FOR CLIENT: ").append(clientName.toUpperCase()).append(" ===\n\n");
        summary.append("Total Operations: ").append(logs.size()).append("\n");
        summary.append("Latest Activity: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(logs.get(0).getTimestamp())).append("\n\n");
        
        summary.append("Recent Operations:\n");
        summary.append("------------------\n");
        
        for (int i = 0; i < Math.min(logs.size(), 10); i++) {
            AuditLog log = logs.get(i);
            summary.append(String.format("[%s] %s - %s (IP: %s)\n",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(log.getTimestamp()),
                log.getOperation(),
                log.getDetails(),
                log.getIpAddress() != null ? log.getIpAddress() : "N/A"
            ));
        }
        
        if (logs.size() > 10) {
            summary.append("... and ").append(logs.size() - 10).append(" more operations\n");
        }
        
        return summary.toString();
    }
}