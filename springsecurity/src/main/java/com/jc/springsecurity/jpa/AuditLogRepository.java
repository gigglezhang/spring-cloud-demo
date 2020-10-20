package com.jc.springsecurity.jpa;

import com.jc.springsecurity.pojo.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jincheng.zhang
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
