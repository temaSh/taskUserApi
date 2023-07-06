package com.example.taskuserapi.service;

import com.example.taskuserapi.entity.UserEntity;
import com.example.taskuserapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public UserEntity editUser(Long userId, UserEntity userEntity) {
        UserEntity existingUserEntity = userRepository.findById(userId).orElse(null);
        if (existingUserEntity != null) {
            existingUserEntity.setEmail(userEntity.getEmail());
            existingUserEntity.setFirstName(userEntity.getFirstName());
            existingUserEntity.setLastName(userEntity.getLastName());
            existingUserEntity.setBirthDate(userEntity.getBirthDate());
            existingUserEntity.setAddress(userEntity.getAddress());
            existingUserEntity.setPhoneNumber(userEntity.getPhoneNumber());
            return userRepository.save(existingUserEntity);
        } else
            return null;

    }

    @Override
    public UserEntity replaceUser(Long userId, UserEntity userEntity) {
        UserEntity existingUserEntity = userRepository.findById(userId).orElse(null);
        if (existingUserEntity != null) {
            userEntity.setId(existingUserEntity.getId());
            return userRepository.save(userEntity);
        } else
            return null;
    }

    @Override
    public boolean deleteUser(Long userId) {
        return false;
    }

    @Override
    public List<UserEntity> searchUsersByBirthDateRange(LocalDate from, LocalDate to) {
        return userRepository.findByBirthDateBetween(from, to);
    }
}

