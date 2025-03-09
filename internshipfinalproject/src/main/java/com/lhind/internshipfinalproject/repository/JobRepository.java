package com.lhind.internshipfinalproject.repository;

import com.lhind.internshipfinalproject.utils.Queries;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.lhind.internshipfinalproject.entity.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {
    @Query(Queries.SEARCH_JOBS)
    Page<Job> searchJobs(
            @Param("title") String title,
            @Param("location") String location,
            @Param("employer") String employer,
            Pageable pageable
    );

    @Query(Queries.EMPLOYER_JOBS_FILTERED)
    Page<Job> findEmployerJobs(
            @Param("employerId") Integer employerId,
            @Param("title") String title,
            @Param("location") String location,
            Pageable pageable
    );

    Page<Job> findByEmployerId(Integer employerId, Pageable pageable);
}