package com.jc.springsecurity.pojo.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String status;
    private String method;
    private String username;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedBy
    private LocalDateTime modifyTime;
}
