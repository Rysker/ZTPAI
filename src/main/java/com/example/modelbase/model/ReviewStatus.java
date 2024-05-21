package com.example.modelbase.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "review_status", schema = "public", indexes = {
        @Index(name = "review_status_id_name_name1_key", columnList = "name", unique = true)
})
public class ReviewStatus
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_status_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

}