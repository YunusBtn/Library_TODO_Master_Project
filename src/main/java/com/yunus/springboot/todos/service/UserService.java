package com.yunus.springboot.todos.service;

import com.yunus.springboot.todos.request.PasswordUpdateRequest;
import com.yunus.springboot.todos.response.UserResponse;

public interface UserService {
    UserResponse getUserInfo();
    void deleteUser();
    void updatePassword(PasswordUpdateRequest passwordUpdateRequest);
}
