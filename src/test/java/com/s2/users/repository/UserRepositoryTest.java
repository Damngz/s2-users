package com.s2.users.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.s2.users.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
  @Autowired
  private UserRepository userRepository;

  @Test
  public void saveUserRepositoryTest() {
    User user = new User();
    user.setUsername("UserTest");
    user.setPassword("12345678");
    user.setEmail("usertest@example.com");
    user.setName("User Test");
    user.setAddress("Test address");
    user.setRole("USER");
  
    User result = userRepository.save(user);

    assertNotNull(result.getId());
    assertEquals("UserTest", result.getUsername());
  }

  @Test
  public void updateUserRepositoryTest() {
    User user = new User();
    user.setUsername("UserTest");
    user.setPassword("12345678");
    user.setEmail("usertest@example.com");
    user.setName("User Test");
    user.setAddress("Test address");
    user.setRole("USER");
  
    userRepository.save(user);

    user.setUsername("UpdatedUser");
    user.setEmail("updateduser@example.com");

    userRepository.save(user);

    User updatedUser = userRepository.findById(user.getId()).orElse(null);

    assertNotNull(updatedUser);
    assertEquals("UpdatedUser", updatedUser.getUsername());
    assertEquals("updateduser@example.com", updatedUser.getEmail());
  }
}