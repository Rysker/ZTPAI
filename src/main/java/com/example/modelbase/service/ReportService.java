package com.example.modelbase.service;

import com.example.modelbase.dto.response.MessageResponseDto;
import com.example.modelbase.dto.response.ReportResponseDto;
import com.example.modelbase.model.*;
import com.example.modelbase.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService
{
    private final UserService userService;
    private final ReviewRepository reviewRepository;
    private final ReportRepository reportRepository;
    private final ReasonRepository reasonRepository;
    private final ReportStatusRepository reportStatusRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final ReviewStatusRepository reviewStatusRepository;
    private final UserRepository userRepository;

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

    public List<ReportResponseDto> getAllReports() throws Exception
    {
        List<ReportResponseDto> result = new LinkedList<>();
        List<Review> reviews = reviewRepository.findAll();
        int size;
        for(Review review: reviews)
        {
            size = review.getReports().size();
            if(size > 0)
            {
                ReportResponseDto report = new ReportResponseDto();
                report.setReportCount(size);
                report.setReviewId(review.getId());
                report.setMainReason(review.findReason());
                report.setReviewContent(review.getContent());
                result.add(report);
            }
        }
        return result;
    }

    @Transactional
    public void verifyReport(Integer reviewId, MessageResponseDto request) throws Exception
    {
        Optional<Review> optionalReview = reviewRepository.getReviewById(reviewId);
        if(optionalReview.isPresent() && optionalReview.get().getReports().size() > 0)
        {
            Review review = optionalReview.get();
            User reviewUser = review.getUser();
            Optional<AccountType> optionalStatus = accountTypeRepository.findByName("SUSPENDED");
            ReviewStatus reviewStatus = reviewStatusRepository.getReviewStatusByName("BLOCKED");

            if (request.getMessage().equals("Ban") && optionalStatus.isPresent())
            {
                review.setReviewStatus(reviewStatus);
                review.getReports().clear();
                reviewRepository.save(review);
                reviewUser.setAccountType(optionalStatus.get());
                userRepository.save(reviewUser);
            }
            else if (request.getMessage().equals("Reject"))
            {
                review.getReports().clear();
                reviewRepository.save(review);
            }
            else
                throw new IllegalArgumentException("Wrong verification type!");
        }
    }
}
