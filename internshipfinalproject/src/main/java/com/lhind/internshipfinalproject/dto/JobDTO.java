package com.lhind.internshipfinalproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobDTO {
    private Integer id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100)
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 2000)
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    // Removed validation constraint because this is set automatically in the controller.
    private Integer employerId;

    private LocalDateTime createdAt;
}
