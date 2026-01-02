package dev.movies.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie,String> {
    Optional<Movie> findByImdbId(String imdbId);
}
