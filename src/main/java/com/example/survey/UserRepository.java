package com.example.survey;

// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.survey.User;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}