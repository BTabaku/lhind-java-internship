package com.lhind.internshipfinalproject.dto;

import jakarta.validation.constraints.NotNull;

public class JobDTO {
    private String title;
    private String description;
    private String location;

    @NotNull(message = "Employer ID is required")
    private Integer employerId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

}
