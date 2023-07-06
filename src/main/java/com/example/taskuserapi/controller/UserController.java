package com.example.taskuserapi.controller;


import com.example.taskuserapi.entity.UserEntity;
import com.example.taskuserapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserEntity> creatUser(@Valid @RequestBody UserEntity userEntity) {
        UserEntity creatUserEntity = userService.createUser(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(creatUserEntity);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long userId) {
        UserEntity userEntity = userService.getUserById(userId);
        if (userEntity != null) {
            return ResponseEntity.ok(userEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserEntity> editUser(@PathVariable Long userId, @Valid @RequestBody UserEntity userEntity){
        UserEntity editUserEntity = userService.editUser(userId, userEntity);
        if (editUserEntity != null){
            return ResponseEntity.ok(editUserEntity);
        } else
            return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserEntity> replaceUser(@PathVariable Long userId, @Valid @RequestBody UserEntity userEntity){
        UserEntity replaceUserEntity = userService.replaceUser(userId, userEntity);
        if (replaceUserEntity != null){
            return ResponseEntity.ok(replaceUserEntity);
        } else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable Long userId){
        boolean deleteUser = userService.deleteUser(userId);
        if (deleteUser){
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

@GetMapping("/search")
    public ResponseEntity<List<UserEntity>> searchUsersByBirthDateRange(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to){
List<UserEntity> userEntities = userService.searchUsersByBirthDateRange(from, to);
return ResponseEntity.ok(userEntities);
}

}
