package com.example.modelbase.repository;

import com.example.modelbase.model.Like;
;
import com.example.modelbase.model.Review;
import com.example.modelbase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer>
{
    Optional<Like> getLikeByUserAndReview(User user, Review review);
}