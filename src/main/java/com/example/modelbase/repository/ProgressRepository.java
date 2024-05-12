package com.example.modelbase.repository;

import com.example.modelbase.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Integer>
{
    Progress findProgressByName(String name);
}
