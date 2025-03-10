package com.lhind.internshipfinalproject.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private Integer id;
    private Integer jobId;
    private Integer employerId;
    private String content;
    private int rating;
}
