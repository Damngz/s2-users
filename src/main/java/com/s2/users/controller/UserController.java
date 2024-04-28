package com.s2.users.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
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
  public CollectionModel<EntityModel<User>> getAllUsers() {
    List<User> users = userService.getAllUsers();

    List<EntityModel<User>> userResources = users.stream()
      .map(user -> EntityModel.of(
        user,
        WebMvcLinkBuilder.linkTo(
          WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(user.getId())
        ).withSelfRel())).collect(Collectors.toList());

    WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
    CollectionModel<EntityModel<User>> resources = CollectionModel.of(userResources, linkTo.withRel("users"));

    return resources;
  }

  @GetMapping("/{id}")
  public EntityModel<User> getUserById(@PathVariable Long id) {
    Optional<User> user = userService.getUserById(id);

    if (user.isPresent()) {
      return EntityModel.of(user.get(),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(id)).withSelfRel(),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers()).withRel("all-users"));
    } else {
      throw new UserNotFoundException("User not found with id: " + id);
    }
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
  public EntityModel<User> createUser(@Validated @RequestBody User user) {
    User createdUser = userService.createUser(user);

    return EntityModel.of(createdUser,
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(createdUser.getId())).withSelfRel(),
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers()).withRel("all-users"));
  }

  @PutMapping("/{id}")
  public EntityModel<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    User updatedUser = userService.updateUser(id, user);

    return EntityModel.of(updatedUser,
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(id)).withSelfRel(),
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers()).withRel("all-users"));
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id); 
  }
}
