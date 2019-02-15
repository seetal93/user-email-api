package com.qa.user.userapi.service;

import org.springframework.http.ResponseEntity;

import com.qa.user.userapi.persistence.domain.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();


	ResponseEntity<Object> deleteUser(Long userId);

	User addUser(User user);

	User getUser(Long userId);

	ResponseEntity<Object> updateUser(User user, Long userId);
}
