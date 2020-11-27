package com.example.survey;

// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.survey.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Integer> {

    // Survey findByEmail(String email);
    Iterable<Survey> findByEmail(String email);

}