package com.s2.users.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s2.users.model.Login;
import com.s2.users.model.Response;
import com.s2.users.model.User;
import com.s2.users.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/users")
public class UserController {

  private static final Logger log = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public Optional<User> getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @PostMapping("/login")
  public Response login(@RequestBody Login userLoginRequest) {
    String username = userLoginRequest.getUsername();
    String password = userLoginRequest.getPassword();

    // Buscar el usuario por nombre de usuario en la base de datos
    User user = userService.getUserByUsername(username);

    // Verificar si el usuario existe y si la contraseña coincide
    if (user != null && user.getPassword().equals(password)) {
      return new Response(200, "Login exitoso"); // Retorna un objeto LoginResponse con estado 200 y mensaje "Login exitoso"
    } else {
      return new Response(401, "Nombre de usuario o contraseña incorrectos"); // Retorna un objeto LoginResponse con estado 401 y mensaje de error
    }
  }

  @PostMapping
  public Response createUser(@RequestBody User user) {
    User createdUser = userService.createUser(user);

    if (createdUser == null) {
      log.error("No se pudo crear el usuario", user);
      return new Response(500, "No se pudo crear el usuario");
    }

    return new Response(200, "Usuario creado con éxito");
  }

  @PutMapping("/{id}")
  public Response updateUser(@PathVariable Long id, @RequestBody User user) {
    try {
      userService.updateUser(id, user);
      return new Response(200, "Usuario actualizado con éxito");
    } catch (Exception e) {
      log.error("No se pudo actualizar el usuario", e);
      return new Response(500, "No se pudo actualizar el usuario.");
    }
  }

  @DeleteMapping("/{id}")
  public Response deleteUser(@PathVariable Long id) {
    try {
      userService.deleteUser(id);
      return new Response(200, "Usuario eliminado con éxito");
    } catch (Exception e) {
      log.error("No se pudo eliminar el usuario", e);
      return new Response(500, "No se pudo eliminar el usuario.");
    }
    
  }
}
