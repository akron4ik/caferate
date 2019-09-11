package workplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import workplace.model.Restaurant;
import workplace.repository.restaurant.CrudRestaurantRepository;

import java.time.LocalDate;
import java.util.List;

import static workplace.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {
    private final CrudRestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(CrudRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @CacheEvict(value = "meals", allEntries = true)
    public Restaurant create(Restaurant restaurant){
        Assert.notNull(restaurant, "restaurant must be not null");
        return restaurantRepository.save(restaurant);
    }

    public Restaurant get(int id){
        return checkNotFoundWithId(restaurantRepository.getRestaurantById(id), id);
    }

    @CacheEvict(value = "meals", allEntries = true)
    public void delete(int id){
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @CacheEvict(value = "meals", allEntries = true)
    public void update(Restaurant restaurant){
        Assert.notNull(restaurant,"restaurant must be not null");
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }

    public List<Restaurant> getAll(){
       return restaurantRepository.getAll();
    }

    public Restaurant getWithMeal(int id){
        return checkNotFoundWithId(restaurantRepository.getWithMeals(id), id);
    }

    public List<Restaurant> getRestaurantsByDate(LocalDate date){
        return restaurantRepository.getRestaurantsByDate(date);
    }

}
