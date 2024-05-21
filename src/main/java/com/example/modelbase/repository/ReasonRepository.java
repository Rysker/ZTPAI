package com.example.modelbase.repository;

import com.example.modelbase.model.Reason;
import com.example.modelbase.model.Report;
import com.example.modelbase.model.Review;
import com.example.modelbase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReasonRepository extends JpaRepository<Reason, Integer>
{
    Reason findReasonByReasonName(String name);
}

