package com.lhind.internshipfinalproject.repository;

import com.lhind.internshipfinalproject.entity.User;
import com.lhind.internshipfinalproject.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Page<User> findByRole(Role role, Pageable pageable);
}