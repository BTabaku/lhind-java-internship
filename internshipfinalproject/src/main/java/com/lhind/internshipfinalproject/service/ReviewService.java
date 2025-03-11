package com.lhind.internshipfinalproject.service;

import com.lhind.internshipfinalproject.dto.ReviewDto;
import com.lhind.internshipfinalproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    ReviewDto addReview(Integer jobId, ReviewDto reviewDto, User employer);
    Page<ReviewDto> getReviews(Integer jobId, Integer rating, Pageable pageable);
}
