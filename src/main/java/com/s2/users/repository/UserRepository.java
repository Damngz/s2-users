package com.s2.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s2.users.model.User;

public interface UserRepository extends JpaRepository <User, Long> {}
