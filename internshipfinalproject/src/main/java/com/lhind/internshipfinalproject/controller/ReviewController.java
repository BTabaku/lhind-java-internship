package com.lhind.internshipfinalproject.controller;

import com.lhind.internshipfinalproject.dto.ReviewDto;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employer/reviews")  // Changed path to "employer"
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // Existing GET for listing reviews, now under /api/v1/employer/reviews
    @GetMapping
    public Page<ReviewDto> getReviews(
            @RequestParam(required = false) Integer jobId,
            @RequestParam(required = false) Integer rating,
            Pageable pageable) {
        return reviewService.getReviews(jobId, rating, pageable);
    }

    // NEW: Add a review for a specific job
    @PostMapping("/jobs/{jobId}")
    public ResponseEntity<ReviewDto> addReview(
            @PathVariable Integer jobId,
            @RequestBody ReviewDto reviewDto,
            @AuthenticationPrincipal User user) {

        // user should have Role.EMPLOYER
        // The service method also checks if the user is truly the job's employer
        ReviewDto savedReview = reviewService.addReview(jobId, reviewDto, user);
        return ResponseEntity.ok(savedReview);
    }
}
