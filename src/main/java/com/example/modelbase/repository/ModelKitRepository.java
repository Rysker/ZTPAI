package com.example.modelbase.repository;

import com.example.modelbase.model.ModelKit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelKitRepository extends JpaRepository<ModelKit, Integer>
{
    Optional<ModelKit> findById(int id);
}
