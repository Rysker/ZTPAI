package com.example.modelbase.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "report_status", schema = "public", indexes = {
        @Index(name = "report_status_status_name_status_name1_key", columnList = "status_name", unique = true)
})
public class ReportStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_status_id", nullable = false)
    private Integer id;

    @Column(name = "status_name", nullable = false)
    private String statusName;
}