package com.example.modelbase.models;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_type", schema = "public", indexes = {
        @Index(name = "vehicle_type_name_name1_key", columnList = "name", unique = true)
})
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_type_id", nullable = false)
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