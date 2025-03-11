package com.lhind.internshipfinalproject.service.impl;

import com.lhind.internshipfinalproject.dto.ReviewDto;
import com.lhind.internshipfinalproject.entity.Job;
import com.lhind.internshipfinalproject.entity.Review;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.mapper.ReviewMapper;
import com.lhind.internshipfinalproject.repository.JobRepository;
import com.lhind.internshipfinalproject.repository.ReviewRepository;
import com.lhind.internshipfinalproject.repository.UserRepository;
import com.lhind.internshipfinalproject.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             JobRepository jobRepository,
                             UserRepository userRepository,
                             ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewDto addReview(Integer jobId, ReviewDto reviewDto, User employer) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        // Ensure that the current employer is the owner of the job posting
        if (!job.getEmployer().getId().equals(employer.getId())) {
            throw new RuntimeException("Unauthorized: you can only review jobs that you posted.");
        }
        Review review = reviewMapper.toEntity(reviewDto);
        review.setJob(job);
        review.setEmployer(employer);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toDTO(savedReview);
    }

    @Override
    public Page<ReviewDto> getReviews(Integer jobId, Integer rating, Pageable pageable) {
        return reviewRepository.findReviewsByJobAndRating(jobId, rating, pageable)
                .map(reviewMapper::toDTO);
    }
}
