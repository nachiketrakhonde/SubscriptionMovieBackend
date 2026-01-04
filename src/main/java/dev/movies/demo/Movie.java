package dev.movies.demo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    private String id;
    private String imdbId;
    private String title;
    private List<String> genres;
    private String releaseDate;
    private List<String> reviewIds;
    private String trailerLink;
    private List<String> backdrops;

}
