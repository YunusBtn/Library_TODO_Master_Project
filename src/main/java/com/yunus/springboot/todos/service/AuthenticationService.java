package com.yunus.springboot.todos.service;

import com.yunus.springboot.todos.request.AuthenticationRequest;
import com.yunus.springboot.todos.request.RegisterRequest;
import com.yunus.springboot.todos.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegisterRequest input) throws Exception;
    AuthenticationResponse login(AuthenticationRequest request);
}
