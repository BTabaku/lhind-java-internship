package com.lhind.internshipfinalproject.repository;

import com.lhind.internshipfinalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
