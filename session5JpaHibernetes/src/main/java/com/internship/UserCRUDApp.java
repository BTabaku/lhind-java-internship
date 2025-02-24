package com.internship;

import com.internship.dao.UserDAO;
import com.internship.entities.User;

public class UserCRUDApp {
    public static void main(String[] args) {
        UserDAO userDao = new UserDAO();

        // Create a new user
        User newUser = new User("user01", "password01", "ROLE_USER");
        userDao.createUser(newUser);
        System.out.println("User created with id: " + newUser.getId());

        // Read the user by id
        User retrievedUser = userDao.getUserById(newUser.getId());
        System.out.println("Retrieved user: " + retrievedUser.getUsername());

        // Update the user's password
        retrievedUser.setPassword("newpassword");
        userDao.updateUser(retrievedUser);
        System.out.println("Updated user password: " + retrievedUser.getPassword());

        // Delete the user
        userDao.deleteUser(retrievedUser.getId());
        System.out.println("User deleted");

        userDao.close();
    }
}