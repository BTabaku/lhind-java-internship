package org.internship.repository;

import org.internship.model.entity.UserDetails;

import java.util.List;
public interface UserDetailsRepository {
    void save(UserDetails userDetails);
    UserDetails findById(Long id);
    java.util.List<UserDetails> findAll();
    void update(UserDetails userDetails);
    void delete(UserDetails userDetails);
}
