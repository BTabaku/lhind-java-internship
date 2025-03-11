package com.lhind.internshipfinalproject.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private Integer id;
    private Integer jobId;
    private Integer employerId;

    // Matches the renamed field in Review.java
    private String content;

    private int rating;
}
