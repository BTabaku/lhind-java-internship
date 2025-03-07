package com.lhind.internshipfinalproject.repository;

import com.lhind.internshipfinalproject.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {

//    Filter Job by title or Location
    Page<Job> findByTitleOrLocation(String title, String location, Pageable pageable);

}
