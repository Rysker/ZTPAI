package com.example.modelbase.repository;

import com.example.modelbase.model.ReportStatus;
import com.example.modelbase.model.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportStatusRepository extends JpaRepository<ReportStatus, Integer>
{
    ReportStatus findReportStatusByStatusName(String name);
}

