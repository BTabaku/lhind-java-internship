package com.lhind.internshipfinalproject.utils;

public class Queries {

    // Job-related queries
    public static final String SEARCH_JOBS =
            "SELECT j FROM Job j WHERE " +
                    "(:title IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
                    "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
                    "(:employer IS NULL OR LOWER(j.employer.username) LIKE LOWER(CONCAT('%', :employer, '%')))";

    public static final String EMPLOYER_JOBS_FILTERED =
            "SELECT j FROM Job j WHERE " +
                    "j.employer.id = :employerId AND " +
                    "(:title IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
                    "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%')))";

    // Application-related queries
    public static final String APPLICATIONS_FOR_JOB =
            "SELECT a FROM Application a WHERE " +
                    "a.job.id = :jobId AND " +
                    "(:status IS NULL OR a.status = :status)";

    // NEW: Filter by jobSeekerId + job title
    public static final String APPLICATIONS_BY_JOBSEEKER_AND_TITLE =
            "SELECT a FROM Application a " +
                    "WHERE a.jobSeeker.id = :jobSeekerId " +
                    "AND LOWER(a.job.title) LIKE LOWER(CONCAT('%', :title, '%'))";

    // NEW: Filter by jobSeekerId + status + job title
    public static final String APPLICATIONS_BY_JOBSEEKER_AND_STATUS_AND_TITLE =
            "SELECT a FROM Application a " +
                    "WHERE a.jobSeeker.id = :jobSeekerId " +
                    "AND a.status = :status " +
                    "AND LOWER(a.job.title) LIKE LOWER(CONCAT('%', :title, '%'))";

    // Review-related queries
    public static final String REVIEWS_BY_JOB_AND_RATING =
            "SELECT r FROM Review r WHERE " +
                    "r.job.id = :jobId AND " +
                    "(:rating IS NULL OR r.rating = :rating)";
}
