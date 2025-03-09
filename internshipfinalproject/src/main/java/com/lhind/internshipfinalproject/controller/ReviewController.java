package com.lhind.internshipfinalproject.controller;

import com.lhind.internshipfinalproject.dto.ReviewDto;
import com.lhind.internshipfinalproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/employee/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public Page<ReviewDto> getReviews(
            @RequestParam(required = false) Integer jobId,
            @RequestParam(required = false) Integer rating,
            Pageable pageable) {
        return reviewService.getReviews(jobId, rating, pageable);
    }
}