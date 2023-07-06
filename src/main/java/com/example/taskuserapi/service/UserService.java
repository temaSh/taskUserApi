package com.example.taskuserapi.service;


import com.example.taskuserapi.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    UserEntity createUser(UserEntity userEntity);

    UserEntity getUserById(Long userId);

    UserEntity editUser(Long userId, UserEntity userEntity);

    UserEntity replaceUser(Long userId, UserEntity userEntity);

    boolean deleteUser(Long userId);

    List<UserEntity> searchUsersByBirthDateRange(LocalDate from, LocalDate to);
}

