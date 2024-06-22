package com.restapi.react.reactapi.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class TodoResource {

    private TodoService todoService;

    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(path = "users/{username}/todos")
    public List<Todo> retriveTodos(@PathVariable String username) {
        return todoService.findByUsername(username);
    }

    @GetMapping(path = "users/{username}/todos/{id}")
    public Todo retriveTodo(@PathVariable String username, @PathVariable int id) {
        return todoService.findById(id);
    }

    @DeleteMapping(path = "users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username,
            @PathVariable int id, @RequestBody Todo todo) {
        todoService.updateTodo(todo);
        return todo;
    }

    @PostMapping(path = "users/{username}/todos")
    public Todo createTodo(@PathVariable String username,
            @RequestBody Todo todo) {
        Todo createdTodo = todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
        return createdTodo;
    }

}
