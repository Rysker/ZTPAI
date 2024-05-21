package com.example.modelbase.service;

import com.example.modelbase.model.*;
import com.example.modelbase.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService
{
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final ReportRepository reportRepository;
    private final ReasonRepository reasonRepository;
    private final ReportStatusRepository reportStatusRepository;

    public void addReport(String token, Integer reviewId) throws Exception
    {
        User currentUser = userService.getUserFromToken(token);
        Optional<Review> review = reviewRepository.getReviewById(reviewId);
        if(review.isPresent())
        {
            Optional<Report> report = reportRepository.findReportByUserAndReview(currentUser, review.get());
            if(report.isEmpty())
            {
                Report newReport = new Report();
                Reason reason = reasonRepository.findReasonByReasonName("Inappropriate Language");
                ReportStatus reportStatus = reportStatusRepository.findReportStatusByStatusName("PENDING");
                newReport.setReportStatus(reportStatus);
                newReport.setReason(reason);
                newReport.setReview(review.get());
                newReport.setUser(currentUser);
                reportRepository.save(newReport);
            }
        }
        else
            throw new IllegalArgumentException("No review found!");
    }
}
