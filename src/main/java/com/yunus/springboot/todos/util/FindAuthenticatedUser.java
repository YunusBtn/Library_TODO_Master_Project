package com.yunus.springboot.todos.util;

import com.yunus.springboot.todos.entity.User;

public interface FindAuthenticatedUser {
    User getAuthenticatedUser();
}
