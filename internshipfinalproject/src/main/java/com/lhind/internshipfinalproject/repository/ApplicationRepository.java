package com.lhind.internshipfinalproject.repository;

import com.lhind.internshipfinalproject.entity.Application;
import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import com.lhind.internshipfinalproject.utils.Queries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    @Query(Queries.APPLICATIONS_FOR_JOB)
    Page<Application> getApplicationsByJobAndStatus(
            @Param("jobId") Integer jobId,
            @Param("status") ApplicationStatus status,
            Pageable pageable
    );

    Page<Application> findByJobSeekerId(Integer jobSeekerId, Pageable pageable);

    Page<Application> findByJobSeekerIdAndStatus(
            Integer jobSeekerId,
            ApplicationStatus status,
            Pageable pageable
    );
}