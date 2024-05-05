package com.example.modelbase.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reason", schema = "public", indexes = {
        @Index(name = "reason_reason_name_reason_name1_key", columnList = "reason_name", unique = true)
})
public class Reason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reason_id", nullable = false)
    private Integer id;

    @Column(name = "reason_name", nullable = false, length = Integer.MAX_VALUE)
    private String reasonName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

}