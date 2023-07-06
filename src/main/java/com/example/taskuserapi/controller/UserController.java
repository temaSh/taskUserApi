package com.example.taskuserapi.controller;


import com.example.taskuserapi.entity.User;
import com.example.taskuserapi.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> creatUser(@Valid @RequestBody User user) {
        User creatUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(creatUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> editUser(@PathVariable Long userId, @Valid @RequestBody User user){
        User editUser = userService.editUser(userId, user);
        if (editUser != null){
            return ResponseEntity.ok(editUser);
        } else
            return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> replaceUser(@PathVariable Long userId, @Valid @RequestBody User user){
        User replaceUser = userService.replaceUser(userId, user);
        if (replaceUser != null){
            return ResponseEntity.ok(replaceUser);
        } else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Long userId){
        boolean deleteUser = userService.deleteUser(userId);
        if (deleteUser){
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

@GetMapping("/search")
    public ResponseEntity<List<User>> searchUsersByBirthDateRange(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to){
List<User> users = userService.searchUsersByBirthDateRange(from, to);
return ResponseEntity.ok(users);
}

}
