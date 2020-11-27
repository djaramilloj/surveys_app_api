package com.example.demo;

// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Integer> {

    // Survey findByEmail(String email);
    Iterable<Survey> findByEmail(String email);

}