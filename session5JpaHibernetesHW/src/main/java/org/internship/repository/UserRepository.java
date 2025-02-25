package org.internship.repository;

import org.internship.model.entity.User;
import java.util.List;

public interface UserRepository {

    // create new user
    void save(User user);


    //Retrieve all users by their primary key
    User findById(Long id);

    // Retrieve all users list

    List<User> findAll();

    // Update an existing user
    void update(User user);

    // Delete an existing user
    void delete(User user);

}
