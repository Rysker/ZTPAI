package com.example.modelbase.repository;

import com.example.modelbase.model.Progress;
import com.example.modelbase.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>
{
    Optional<Review> getReviewById(int id);
}

