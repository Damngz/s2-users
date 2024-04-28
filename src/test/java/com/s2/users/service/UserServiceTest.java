package com.s2.users.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.s2.users.model.User;
import com.s2.users.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepositoryMock;

  @Test
  public void saveUserTest() {
    User user = new User();
    user.setUsername("UserTest");
    user.setPassword("12345678");
    user.setEmail("usertest@example.com");
    user.setName("User Test");
    user.setAddress("Test address");
    user.setRole("USER");

    when(userRepositoryMock.save(any())).thenReturn(user);

    User result = userService.createUser(user);

    assertEquals("usertest@example.com", result.getEmail());
  }
}
