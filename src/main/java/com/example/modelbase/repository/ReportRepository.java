package com.example.modelbase.repository;

import com.example.modelbase.model.ModelKit;
import com.example.modelbase.model.Report;
import com.example.modelbase.model.Review;
import com.example.modelbase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>
{
    Optional<Report> findReportByUserAndReview(User user, Review review);
}

