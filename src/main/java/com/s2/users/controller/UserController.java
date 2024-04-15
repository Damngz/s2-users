package com.s2.users.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s2.users.model.User;
import com.s2.users.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> getAllMovies() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public Optional<User> getMovieById(@PathVariable Long id) {
    return userService.getUserById(id);
  }
}
