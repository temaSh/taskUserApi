package com.example.taskuserapi.service;

import com.example.taskuserapi.entity.UserEntity;
import com.example.taskuserapi.exception.InvalidDateRangeException;
import com.example.taskuserapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final int minimumAge;

    public UserServiceImpl(UserRepository userRepository, @Value("${minimum.age}") int minimumAge) {
        this.userRepository = userRepository;
        this.minimumAge = minimumAge;
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        LocalDate currentDate = LocalDate.now();
        if (userEntity.getBirthDate().isAfter(currentDate.minusYears(minimumAge))) {
            throw new InvalidDateRangeException("User must be at least " + minimumAge + " years old.");
        }
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserById(Long userId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    @Override
    public UserEntity editUser(Long userId, UserEntity userEntity) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserEntity existingUser = optionalUser.get();
            existingUser.setEmail(userEntity.getEmail());
            existingUser.setFirstName(userEntity.getFirstName());
            existingUser.setLastName(userEntity.getLastName());
            existingUser.setBirthDate(userEntity.getBirthDate());
            existingUser.setAddress(userEntity.getAddress());
            existingUser.setPhoneNumber(userEntity.getPhoneNumber());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    @Override
    public UserEntity replaceUser(Long userId, UserEntity userEntity) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserEntity existingUser = optionalUser.get();
            existingUser.setEmail(userEntity.getEmail());
            existingUser.setFirstName(userEntity.getFirstName());
            existingUser.setLastName(userEntity.getLastName());
            existingUser.setBirthDate(userEntity.getBirthDate());
            existingUser.setAddress(userEntity.getAddress());
            existingUser.setPhoneNumber(userEntity.getPhoneNumber());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteUser(Long userId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserEntity> searchUsersByBirthDateRange(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            throw new InvalidDateRangeException("Invalid date range. 'From' date must be before 'To' date.");
        }
        return userRepository.findByBirthDateBetween(from, to);
    }
}

