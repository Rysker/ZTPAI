package com.example.modelbase.models;

import jakarta.persistence.*;

@Entity
@Table(name = "progress", schema = "public", indexes = {
        @Index(name = "progress_name_name1_key", columnList = "name", unique = true)
})
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}