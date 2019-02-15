package com.qa.user.userapi.service;

import com.qa.user.userapi.persistence.domain.User;
import com.qa.user.userapi.persistence.repository.UserRepository;
import com.qa.user.userapi.util.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public List<User> getUsers() {
        return repo.findAll();
    }

    @Override
    public User getUser(Long userId) {
        Optional<User> user = repo.findById(userId);
        return user.orElseThrow(() -> new UserNotFoundException(userId.toString()));
    }

    @Override
    public User addUser(User user) {
        return repo.save(user);
    }

    @Override
    public ResponseEntity<Object> deleteUser(Long userId) {
        if(userExists(userId)){
            repo.deleteById(userId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


	@Override
    public ResponseEntity<Object> updateUser(User user, Long userId) {
        if(userExists(userId)){
            user.setUserId(userId);
            repo.save(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    private boolean userExists(Long userId){
        Optional<User> userOptional = repo.findById(userId);
        return userOptional.isPresent();
    }

}
