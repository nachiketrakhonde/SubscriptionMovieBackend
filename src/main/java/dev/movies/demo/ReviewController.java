package dev.movies.demo;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final MovieService movieService;

    public ReviewController(ReviewService reviewService, MovieService movieService) {
        this.reviewService = reviewService;
        this.movieService = movieService;
    }

    @PostMapping("/{imdbId}")
    public Review createReview(
            @PathVariable String imdbId,
            @RequestBody Review review) {

        Review savedReview = reviewService.createReview(review);
        movieService.addReviewToMovie(imdbId, savedReview);

        return savedReview;
    }
}