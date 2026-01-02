package dev.movies.demo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MongoTemplate mongoTemplate;

    public MovieService(MovieRepository movieRepository, MongoTemplate mongoTemplate) {
        this.movieRepository = movieRepository;
        this.mongoTemplate = mongoTemplate;
    }
    public Movie createMovie(Movie movie){
        return movieRepository.save(movie);
    }
    public void addReviewToMovie(String imdbId,Review review){
        mongoTemplate.update(Movie.class).matching(Criteria.where("imdbId").is(imdbId)).apply(new Update().push("reviewIds").value(review.getId())).first();
    }

}
