package com.example.modelbase.models;

import jakarta.persistence.*;

@Entity
@Table(name = "report_status", schema = "public", indexes = {
        @Index(name = "report_status_status_name_status_name1_key", columnList = "status_name", unique = true)
})
public class ReportStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_status_id", nullable = false)
    private Integer id;

    @Column(name = "status_name", nullable = false)
    private Integer statusName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatusName() {
        return statusName;
    }

    public void setStatusName(Integer statusName) {
        this.statusName = statusName;
    }

}