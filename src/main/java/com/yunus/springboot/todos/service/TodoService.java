package com.yunus.springboot.todos.service;

import com.yunus.springboot.todos.request.TodoRequest;
import com.yunus.springboot.todos.response.TodoResponse;

import java.util.List;

public interface TodoService {
    List<TodoResponse> getAllTodos();
    TodoResponse createTodo(TodoRequest todoRequest);
    TodoResponse toggleTodoCompletion(long id);
    void deleteTodo(long id);
}
