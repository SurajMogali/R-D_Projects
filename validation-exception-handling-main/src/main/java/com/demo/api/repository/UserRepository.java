package com.demo.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.api.entity.User;

public interface UserRepository  extends JpaRepository<User,Integer> {
    User findByUserId(int id);
    
    List<User> findByEmail(String email);
}
