package com.demo.spring.repository2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.spring.entity.User;

@Repository
public interface UserRepository2 extends JpaRepository<User, Integer> {

}
