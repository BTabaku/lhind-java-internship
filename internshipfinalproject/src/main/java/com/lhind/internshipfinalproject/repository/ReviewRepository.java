package com.lhind.internshipfinalproject.repository;

import com.lhind.internshipfinalproject.entity.Review;
import com.lhind.internshipfinalproject.utils.Queries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query(Queries.REVIEWS_BY_JOB_AND_RATING)
    Page<Review> findReviewsByJobAndRating(
            @Param("jobId") Integer jobId,
            @Param("rating") Integer rating,
            Pageable pageable
    );
}