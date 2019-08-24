package workplace.web.restaurant;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import workplace.model.Restaurant;
import workplace.service.RestaurantService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static workplace.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = RestaurantRestController.REST_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    public static final String REST_REST_URL = "/rest/restaurants";
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    private final RestaurantService service;

    @Autowired
    public RestaurantRestController(RestaurantService service){
        this.service = service;
    }

    @GetMapping("/all")
    public List<Restaurant> getAll(){
        log.info("get all restaurants");
        return service.getAll();
    }

   /* @GetMapping("/all")
    public List<Restaurant> getAllWithMeals(){
        log.info("get all restaurants with meals");
        return service.getRestaurantsWithMeals();
    }*/

    /*@GetMapping("/all")
    public List<Restaurant> getAllByDate(@RequestParam LocalDate localDate){
        log.info("get all restaurants by date {}", localDate);
        return service.getRestaurantsByDate(localDate == null ? LocalDate.now() : localDate);
    }*/

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id){
        log.info("get restaurants by id {}", id);
        return service.get(id);
    }

    /*@GetMapping("/{id}")
    public Restaurant getWithMeal(@PathVariable int id){
        log.info("get restaurant with meals by id {}", id);
        return service.getWithMeal(id);

    }*/

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        log.info("delete restaurant by id {}", id);
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        assureIdConsistent(restaurant, id);
        service.update(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        Restaurant created = service.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
