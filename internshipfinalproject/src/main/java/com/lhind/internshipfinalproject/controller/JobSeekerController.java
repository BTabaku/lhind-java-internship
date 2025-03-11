package com.lhind.internshipfinalproject.controller;

import com.lhind.internshipfinalproject.dto.ApplicationDTO;
import com.lhind.internshipfinalproject.dto.JobDTO;
import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import com.lhind.internshipfinalproject.mapper.JobMapper;
import com.lhind.internshipfinalproject.repository.UserRepository;
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
    private final UserRepository userRepository;

    @GetMapping("/applications")
    public Page<ApplicationDTO> getMyApplications(
            @AuthenticationPrincipal org.springframework.security.core.userdetails.User principal,
            @RequestParam(required = false) ApplicationStatus status,
            @RequestParam(required = false) String title,
            Pageable pageable
    ) {
        User user = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Integer jobSeekerId = user.getId();

        if (status != null && title != null && !title.isBlank()) {
            return applicationService.getApplicationsByJobSeekerStatusAndTitle(jobSeekerId, status, title, pageable);
        } else if (status != null) {
            return applicationService.getApplicationsByJobSeekerAndStatus(jobSeekerId, status, pageable);
        } else if (title != null && !title.isBlank()) {
            return applicationService.getApplicationsByJobSeekerAndTitle(jobSeekerId, title, pageable);
        } else {
            return applicationService.getApplicationsByJobSeeker(jobSeekerId, pageable);
        }
    }

    @PostMapping("/applications")
    public ResponseEntity<ApplicationDTO> applyForJob(
            @AuthenticationPrincipal org.springframework.security.core.userdetails.User principal,
            @Valid @RequestBody ApplicationDTO applicationDTO) {

        User user = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
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
            @AuthenticationPrincipal org.springframework.security.core.userdetails.User principal,
            @RequestParam("file") MultipartFile file) throws IOException {

        // Lookup the full user entity using the principal's username
        User user = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        // Use the current working directory as base (this will be your project folder if run from there)
        String baseUploadDir = System.getProperty("user.dir") + "/uploads/resumes/";
        Path uploadPath = Paths.get(baseUploadDir);
        if (!uploadPath.toFile().exists()) {
            boolean created = uploadPath.toFile().mkdirs();
            if (!created) {
                return ResponseEntity.status(500).body("Could not create upload directory");
            }
        }

        // Sanitize the original filename
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return ResponseEntity.badRequest().body("File name is missing");
        }
        String cleanFileName = Paths.get(originalFilename).getFileName().toString();
        cleanFileName = cleanFileName.replaceAll("\\s+", "_");

        // Prefix the file name with the user's ID to avoid collisions
        String fileName = user.getId() + "_" + cleanFileName;

        File destFile = new File(uploadPath.toFile(), fileName);
        file.transferTo(destFile);

        return ResponseEntity.ok("Resume uploaded successfully to " + destFile.getAbsolutePath());
    }


}
