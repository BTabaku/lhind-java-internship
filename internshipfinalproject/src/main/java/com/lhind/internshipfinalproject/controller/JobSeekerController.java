package com.lhind.internshipfinalproject.controller;

import com.lhind.internshipfinalproject.dto.ApplicationDTO;
import com.lhind.internshipfinalproject.dto.JobDTO;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import com.lhind.internshipfinalproject.mapper.JobMapper;
import com.lhind.internshipfinalproject.service.ApplicationService;
import com.lhind.internshipfinalproject.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/jobseeker")
@RequiredArgsConstructor
@Slf4j
public class JobSeekerController {

    private final ApplicationService applicationService;
    private final JobService jobService;
    private final JobMapper jobMapper;

    @GetMapping("/applications")
    public Page<ApplicationDTO> getMyApplications(
            @AuthenticationPrincipal User user, // Get jobSeekerId from authenticated user
            @RequestParam(required = false) ApplicationStatus status,
            Pageable pageable
    ) {
        Integer jobSeekerId = user.getId();
        if (status != null) {
            return applicationService.getApplicationsByJobSeekerAndStatus(jobSeekerId, status, pageable);
        }
        return applicationService.getApplicationsByJobSeeker(jobSeekerId, pageable);
    }

    @PostMapping("/applications")
    public ResponseEntity<ApplicationDTO> applyForJob(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody ApplicationDTO applicationDTO) {

        applicationDTO.setJobSeekerId(user.getId());
        return ResponseEntity.ok(applicationService.applyForJob(applicationDTO));
    }

    @GetMapping("/jobs")
    public Page<JobDTO> viewAllJobs(

            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String employer,
            Pageable pageable) {

        return jobService.searchJobs(title, location, employer, pageable)
                .map(jobMapper::toDTO);
    }

    @PostMapping("/upload-resume")
    public ResponseEntity<String> uploadResume(
            @RequestParam("jobSeekerId") Integer jobSeekerId,
            @RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        String uploadDir = "uploads/resumes/";
        Path uploadPath = Paths.get(uploadDir);

        // Ensure the directory exists
        if (!uploadPath.toFile().exists()) {
            uploadPath.toFile().mkdirs();
        }

        String fileName = jobSeekerId + "_" + file.getOriginalFilename();
        File destFile = new File(uploadPath.toFile(), fileName);
        file.transferTo(destFile);

        log.info("Resume uploaded successfully: {}", destFile.getAbsolutePath());

        return ResponseEntity.ok("Resume uploaded successfully");
    }
}
