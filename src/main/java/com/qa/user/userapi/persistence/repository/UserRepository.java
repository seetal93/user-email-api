package com.qa.user.userapi.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.user.userapi.persistence.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
