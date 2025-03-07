package com.lhind.internshipfinalproject.service;

import com.lhind.internshipfinalproject.dto.ReviewDto;
import com.lhind.internshipfinalproject.entity.Job;
import com.lhind.internshipfinalproject.entity.Review;
import com.lhind.internshipfinalproject.repository.JobRepository;
import com.lhind.internshipfinalproject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Page<Review> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    public ReviewDto addReview(Integer jobId, ReviewDto reviewDto) {
        Review review = new Review();
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());
        // Add job and employee linking logic here
        return reviewMapper.toDTO(reviewRepository.save(review));
    }
}
