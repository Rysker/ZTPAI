package com.example.modelbase.model;

import jakarta.persistence.*;

@Entity
@Table(name = "manufacturer", schema = "public", indexes = {
        @Index(name = "manufacturer_name_name1_key", columnList = "name", unique = true)
})
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
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