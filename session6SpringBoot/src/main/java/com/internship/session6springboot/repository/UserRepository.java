package com.internship.session6springboot.repository;

import com.internship.session6springboot.enums.RoleEnum;
import com.internship.session6springboot.entity.User;
import org.internship.util.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(Queries.GET_USER_BY_ID)
    Optional<User> findById(@Param("id") Long id);

    @Query(Queries.GET_USER_BY_USERNAME)
    User findByUsername(@Param("username") String username);

    List<User> findByRole(RoleEnum role);

    @Query(Queries.SEARCH_USERS)
    List<User> searchUsers(@Param("keyword") String keyword);
}