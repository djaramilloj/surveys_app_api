package com.example.demo;

// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.User;

// public interface UserRepository extends CrudRepository<User, Integer> {

// }

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}