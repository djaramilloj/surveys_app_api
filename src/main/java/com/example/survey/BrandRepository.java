package com.example.survey;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.survey.Brand;


public interface BrandRepository extends CrudRepository<Brand, Integer> {

    Iterable<Brand> findAll();

}