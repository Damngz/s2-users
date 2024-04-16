package com.s2.users.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="usuario")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank(message = "Debe ingresar un nombre de usuario")
  @Column(name = "username")
  private String username;

  @NotBlank(message = "Debe ingresar una contraseña")
  @Column(name = "password")
  private String password;

  @NotBlank(message = "Debe ingresar un email")
  @Column(name = "email")
  private String email;

  @NotBlank(message = "Debe ingresar un nombre")
  @Column(name = "name")
  private String name;

  @NotBlank(message = "Debe ingresar una dirección")
  @Column(name = "address")
  private String address;

  @NotBlank(message = "Debe ingresar un rol")
  @Column(name = "role")
  private String role;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}