package com.s2.users.service;

import java.util.List;
import java.util.Optional;

import com.s2.users.model.User;

public interface UserService {
  List<User> getAllUsers();
  Optional<User> getUserById(Long id);
}
