package com.example.modelbase.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "account_type", schema = "public", indexes = {
        @Index(name = "account_type_name_name1_key", columnList = "name", unique = true)
})
public class AccountType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_type_id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;
}