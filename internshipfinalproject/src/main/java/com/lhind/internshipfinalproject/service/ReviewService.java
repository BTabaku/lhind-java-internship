package com.lhind.internshipfinalproject.service;

import com.lhind.internshipfinalproject.dto.ReviewDto;
import com.lhind.internshipfinalproject.entity.Job;
import com.lhind.internshipfinalproject.entity.Review;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.mapper.ReviewMapper;
import com.lhind.internshipfinalproject.repository.JobRepository;
import com.lhind.internshipfinalproject.repository.ReviewRepository;
import com.lhind.internshipfinalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper; // Properly injected

    public ReviewDto addReview(Integer jobId, ReviewDto reviewDto, User employer) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Review review = reviewMapper.toEntity(reviewDto);
        review.setJob(job);
        review.setEmployer(employer); // Set the employer instead of employee

        return reviewMapper.toDTO(reviewRepository.save(review));
    }

    public Page<ReviewDto> getReviews(Integer jobId, Integer rating, Pageable pageable) {
        return reviewRepository.findReviewsByJobAndRating(jobId, rating, pageable)
                .map(reviewMapper::toDTO);
    }
}