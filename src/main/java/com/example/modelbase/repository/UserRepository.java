package com.example.modelbase.repository;

import com.example.modelbase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByEmail(String email);
    Optional<User> findUserByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String email);
}
