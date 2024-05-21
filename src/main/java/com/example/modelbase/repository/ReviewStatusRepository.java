package com.example.modelbase.repository;

import com.example.modelbase.model.Review;
import com.example.modelbase.model.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewStatusRepository extends JpaRepository<ReviewStatus, Integer>
{
    ReviewStatus getReviewStatusByName(String name);
}

