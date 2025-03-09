package com.lhind.internshipfinalproject.controller;

import com.lhind.internshipfinalproject.dto.ApplicationDTO;
import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import com.lhind.internshipfinalproject.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/applications")
    public Page<ApplicationDTO> getMyApplications(
            @RequestParam Integer jobSeekerId, // Should come from authentication
            @RequestParam(required = false) ApplicationStatus status,
            Pageable pageable
    ) {
        if (status != null) {
            return applicationService.getApplicationsByJobSeekerAndStatus(jobSeekerId, status, pageable);
        }
        return applicationService.getApplicationsByJobSeeker(jobSeekerId, pageable);
    }

    // ✅ New: Upload Resume
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
