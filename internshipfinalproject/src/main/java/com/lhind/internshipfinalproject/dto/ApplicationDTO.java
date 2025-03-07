package com.lhind.internshipfinalproject.dto;

import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public class ApplicationDTO {

    @NotNull(message = "Job ID is required")
    private Integer jobId;

    @NotNull(message = "Job Seeker ID is required")
    private Integer jobSeekerId;

    private ApplicationStatus status;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(Integer jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
