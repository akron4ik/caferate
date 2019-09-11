package workplace.web.meal;

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
import workplace.model.Meal;
import workplace.model.Restaurant;
import workplace.service.MealService;
import workplace.service.RestaurantService;
import workplace.to.MealTo;
import workplace.util.MealUtil;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static workplace.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = MealRestController.REST_MEAL_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController {
    public static final String REST_MEAL_URL = "/rest/meals";
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    private final MealService service;
    private final RestaurantService restaurantService;

    @Autowired
    public MealRestController(MealService service, RestaurantService restaurantService) {
        this.service = service;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        log.info("get meal with id {}", id);
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete meal with id {}", id);
        service.delete(id);
    }

    @GetMapping
    public List<Meal> getAllByRestaurantId(@RequestParam int restaurantId, @RequestParam(required = false) LocalDate localDate) {
        log.info("get all meals by restaurant id {}", restaurantId);
        return localDate == null ? service.getAllByRestaurantId(restaurantId) : service.getMealsByDate(localDate, restaurantId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody MealTo mealTo, @PathVariable int id) {
        assureIdConsistent(mealTo, id);
        log.info("update meal {}", id);
        service.update(mealTo);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Meal> createWithLocation(@Validated(View.Web.class) @RequestBody MealTo mealTo) {
        Restaurant restaurant = restaurantService.get(mealTo.getRestaurantId());
        Meal created = MealUtil.createNewFromTo(mealTo, restaurant);
        Meal meal = service.create(created);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_MEAL_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(meal);
    }
}