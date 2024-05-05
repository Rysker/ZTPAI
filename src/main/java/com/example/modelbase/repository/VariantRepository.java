package com.example.modelbase.repository;

import com.example.modelbase.model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer>
{
    @Query("SELECT DISTINCT v.name FROM Variant v WHERE v.vehicle.id = ?1")
    List<String> findDistinctByVehicleId(Integer vehicleId);
}