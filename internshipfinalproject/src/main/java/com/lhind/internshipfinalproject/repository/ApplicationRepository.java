package com.lhind.internshipfinalproject.repository;


import com.lhind.internshipfinalproject.entity.Application;
import com.lhind.internshipfinalproject.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Page<Application> findByJobIdAndStatus(Integer jobId, ApplicationStatus status, Pageable pageable);
}
