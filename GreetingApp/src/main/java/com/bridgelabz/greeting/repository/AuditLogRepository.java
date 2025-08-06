package com.bridgelabz.greeting.repository;

import com.bridgelabz.greeting.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    
    List<AuditLog> findByClientNameOrderByTimestampDesc(String clientName);
    
    List<AuditLog> findByClientNameAndTimestampBetweenOrderByTimestampDesc(
        String clientName, Date startDate, Date endDate);
    
    @Query("SELECT a FROM AuditLog a WHERE a.clientName = :clientName AND DATE(a.timestamp) = DATE(:date) ORDER BY a.timestamp DESC")
    List<AuditLog> findByClientNameAndDate(@Param("clientName") String clientName, @Param("date") Date date);
}