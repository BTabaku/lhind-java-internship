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

    public ReviewDto addReview(Integer jobId, ReviewDto reviewDto) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        User employee = userRepository.findById(reviewDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Review review = reviewMapper.toEntity(reviewDto);
        review.setJob(job);
        review.setEmployee(employee);

        return reviewMapper.toDTO(reviewRepository.save(review));
    }

    public Page<ReviewDto> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable)
                .map(reviewMapper::toDTO);
    }
}