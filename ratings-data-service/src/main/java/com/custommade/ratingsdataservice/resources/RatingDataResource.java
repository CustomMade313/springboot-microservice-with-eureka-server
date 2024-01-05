package com.custommade.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.custommade.ratingsdataservice.models.Rating;
import com.custommade.ratingsdataservice.models.UserRating;




@RestController
public class RatingDataResource {

    @GetMapping("/ratingsdata/{movieId}")
    public Rating getMethodName(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping("/ratingsdata/users/{userId}")
    public UserRating getUserRaiting(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
            new Rating("1234", 4),
            new Rating("5678", 3)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
    

    
    
}
