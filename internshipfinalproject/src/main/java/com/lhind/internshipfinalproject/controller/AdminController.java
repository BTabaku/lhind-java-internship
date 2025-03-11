package com.lhind.internshipfinalproject.controller;

import com.lhind.internshipfinalproject.dto.UserDTO;
import com.lhind.internshipfinalproject.enums.Role;
import com.lhind.internshipfinalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Page<UserDTO>> getAllUsers(@RequestParam(required = false) Role role, Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(role, pageable));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Integer id) {
        Optional<UserDTO> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()) {
            UserDTO deletedUser = optionalUser.get();
            userService.deleteUser(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User deleted successfully");
            response.put("user", deletedUser);
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
