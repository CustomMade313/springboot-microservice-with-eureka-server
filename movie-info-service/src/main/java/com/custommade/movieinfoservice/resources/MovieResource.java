package com.custommade.movieinfoservice.resources;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class MovieResource {
    @GetMapping("/movies/{movieId}")
    public Movie getMovie(@PathVariable("movieId") String movieId) {
        return new Movie(movieId, "Test name");
    }
    
}
