package com.lhind.internshipfinalproject.repository;


import com.lhind.internshipfinalproject.entity.Application;
import com.lhind.internshipfinalproject.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findByJobIdAndRating(Integer jobId, Integer rating, Pageable pageable);
}
