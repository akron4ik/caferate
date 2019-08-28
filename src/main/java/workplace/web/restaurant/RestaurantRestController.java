package workplace.web.restaurant;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import workplace.View;
import workplace.model.Restaurant;
import workplace.service.RestaurantService;
import workplace.service.VoiceService;
import workplace.to.RestaurantTo;
import workplace.util.RestaurantUtil;
import workplace.util.Util;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static workplace.util.ValidationUtil.assureIdConsistent;
import static workplace.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantRestController.REST_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    public static final String REST_REST_URL = "/rest/restaurants";
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    private final RestaurantService restaurantService;
    private final VoiceService voiceService;

    @Autowired
    public RestaurantRestController(RestaurantService restaurantService, VoiceService voiceService ){
        this.restaurantService = restaurantService;
        this.voiceService = voiceService;
    }

    @GetMapping("/all")
    public List<Restaurant> getAll(){
        log.info("get all restaurants");
        return restaurantService.getAll();
    }



    @GetMapping("/all/meals")
    public List<Restaurant> getAllWithMeals(){
        log.info("get all restaurants with meals");
        return restaurantService.getRestaurantsWithMeals();
    }

    @GetMapping("/all/date")
    public List<Restaurant> getAllByDate(@RequestParam LocalDate localDate){
        log.info("get all restaurants by date {}", localDate);
        return restaurantService.getRestaurantsByDate(localDate == null ? LocalDate.now() : localDate);
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id){
        log.info("get restaurants by id {}", id);
        return restaurantService.get(id);
    }

    @GetMapping("/meal/{id}")
    public Restaurant getWithMeal(@PathVariable int id){
        log.info("get restaurant with meals by id {}", id);
        return restaurantService.getWithMeal(id);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        log.info("delete restaurant by id {}", id);
        restaurantService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class)@RequestBody Restaurant restaurant, @PathVariable int id) {
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Restaurant> createWithLocation(@Validated(View.Web.class)@RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/rate/{restaurantId}")
    public int getRating(@PathVariable int restaurantId, @RequestParam(required = false) LocalDate localDate){
        log.info("get restaurant rating by restaurant id {} and date{}", restaurantId, localDate);
        return voiceService.getRating(restaurantId, localDate == null ? LocalDate.now() : localDate);
    }

}
