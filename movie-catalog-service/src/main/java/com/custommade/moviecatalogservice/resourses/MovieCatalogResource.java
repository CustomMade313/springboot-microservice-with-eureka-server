package com.custommade.moviecatalogservice.resourses;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
// import org.springframework.web.reactive.function.client.WebClient;

import com.custommade.moviecatalogservice.models.CatalogItem;
import com.custommade.moviecatalogservice.models.Movie;
import com.custommade.moviecatalogservice.models.UserRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;







@RestController
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;


    

    // @Autowired
    // private WebClient.Builder webClientBuilder;


    @GetMapping("/catalog/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class
        );
        
        return ratings.getUserRating().stream().map(
                rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service:8082/movies/" + rating.getMovieId(), Movie.class);
                    
                    // Movie movie = webClientBuilder.build()
                    //      .get()
                    //      .uri("http://localhost:8082/movies/"+ rating.getMovieId())
                    //      .retrieve()
                    //      .bodyToMono(Movie.class)
                    //      .block();

                    return new CatalogItem(movie.getName(), "description", rating.getRating());
                }
        ).collect(Collectors.toList());
       
   }
   
}
