package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Brand;


public interface BrandRepository extends CrudRepository<Brand, Integer> {

    Iterable<Brand> findAll();

}