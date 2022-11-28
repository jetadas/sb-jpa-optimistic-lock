package com.sm.repository;

import com.sm.entity.Movie;
import org.springframework.data.repository.CrudRepository;


public interface MovieRepository extends CrudRepository<Movie, Long> {

    Movie findByTitle(String title);
}