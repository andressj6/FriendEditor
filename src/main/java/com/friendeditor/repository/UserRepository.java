package com.friendeditor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.friendeditor.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
