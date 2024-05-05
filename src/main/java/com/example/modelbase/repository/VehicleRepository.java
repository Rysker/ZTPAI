package com.example.modelbase.repository;

import com.example.modelbase.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>
{
    Vehicle getVehicleByName(String vehicle_name);

    List<Vehicle> findAll();
    @Query("SELECT DISTINCT v.generation FROM Vehicle v")
    List<String> findDistinctGenerations();

}
