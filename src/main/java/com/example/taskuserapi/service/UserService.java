package com.example.taskuserapi.service;


import com.example.taskuserapi.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserById(Long userId);

    User editUser(Long userId, User user);

    User replaceUser(Long userId, User user);

    boolean deleteUser(Long userId);

    List<User> searchUsersByBirthDateRange(LocalDate from, LocalDate to);
}

