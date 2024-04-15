package com.example.modelbase.models;

import jakarta.persistence.*;

@Entity
@Table(name = "attribute", schema = "public", indexes = {
        @Index(name = "attribute_name_attribute_id_key", columnList = "name", unique = true)
})
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_id", nullable = false)
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