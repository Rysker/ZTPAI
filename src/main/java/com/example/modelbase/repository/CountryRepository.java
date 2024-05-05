package com.example.modelbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.modelbase.model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>
{
    List<Country> findAll();
}
