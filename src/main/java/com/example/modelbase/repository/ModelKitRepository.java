package com.example.modelbase.repository;

import com.example.modelbase.model.ModelKit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelKitRepository extends JpaRepository<ModelKit, Integer>
{
    ModelKit findById(int id);
}
