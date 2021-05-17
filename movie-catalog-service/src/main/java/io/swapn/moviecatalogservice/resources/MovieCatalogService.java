package io.swapn.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swapn.moviecatalogservice.models.CatalogItem;
import io.swapn.moviecatalogservice.models.Movie;
import io.swapn.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogService {

	@Autowired
	RestTemplate template;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		//RestTemplate template = new RestTemplate();
		//Movie movie = template.getForObject("http://localhost:8081/movies/foo", Movie.class);
		List<Rating> ratings = Arrays.asList(new Rating("1234", 4), new Rating("5678", 3));

		return ratings.stream()
					  .map(rating -> {
						Movie movie = template.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);					  
						  return new CatalogItem(movie.getName(), "Desc", rating.getRating());
						  })
					  .collect(Collectors.toList());

	}
}
