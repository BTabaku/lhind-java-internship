package com.lhind.internshipfinalproject.dto;

import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public class ApplicationDTO {

    @NotNull(message = "Job ID is required")
    private Integer jobId;

    @NotNull(message = "Job Seeker ID is required")
    private Integer jobSeekerId;

    private ApplicationStatus status;

}
