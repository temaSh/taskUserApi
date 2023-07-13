package com.example.taskuserapi.controller;

import com.example.taskuserapi.entity.UserEntity;
import com.example.taskuserapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity user) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = user.getBirthDate();
        int age = Period.between(birthDate, currentDate).getYears();
        if (age < 18) {
            throw new IllegalArgumentException("User must be at least 18 years old.");
        }
        UserEntity createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long userId) {
        UserEntity user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserEntity> editUser(@PathVariable Long userId, @Valid @RequestBody UserEntity user) {
        UserEntity editedUser = userService.editUser(userId, user);
        if (editedUser != null) {
            return ResponseEntity.ok(editedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserEntity> replaceUser(@PathVariable Long userId, @Valid @RequestBody UserEntity user) {
        UserEntity replacedUser = userService.replaceUser(userId, user);
        if (replacedUser != null) {
            return ResponseEntity.ok(replacedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        boolean deleted = userService.deleteUser(userId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserEntity>> searchUsersByBirthDateRange(@RequestParam String from, @RequestParam String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        List<UserEntity> users = userService.searchUsersByBirthDateRange(fromDate, toDate);
        return ResponseEntity.ok(users);
    }
}
