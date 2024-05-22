package com.example.modelbase.repository;

import com.example.modelbase.model.FollowerList;
import com.example.modelbase.model.Reason;
import com.example.modelbase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowerListRepository extends JpaRepository<FollowerList, Integer>
{
    Boolean existsByFollowerAndFollowed(User follower, User followed);
    Optional<FollowerList> getByFollowerAndFollowed(User follower, User followed);
}

