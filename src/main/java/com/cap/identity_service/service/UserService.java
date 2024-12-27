package com.cap.identity_service.service;

import com.cap.identity_service.dto.request.UserCreationRequest;
import com.cap.identity_service.dto.request.UserUpdateRequest;
import com.cap.identity_service.dto.response.UserResponse;
import com.cap.identity_service.entity.User;
import com.cap.identity_service.exception.AppException;
import com.cap.identity_service.exception.ErrorCode;
import com.cap.identity_service.mapper.UserMapper;
import com.cap.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername((request.getUsername()))) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
//        User user = new User();
//
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());

        User user = userMapper.toUser(request);

        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public UserResponse getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found."));
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setPassword(request.getPassword());
//        user.setDob(request.getDob());
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user)) ;
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
