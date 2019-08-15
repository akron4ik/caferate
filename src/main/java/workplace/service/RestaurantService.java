package workplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import workplace.model.Restaurant;
import workplace.repository.restaurant.DataJpaRestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestaurantService {
    private final DataJpaRestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(DataJpaRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @CacheEvict(value = "meals", allEntries = true)
    public Restaurant create(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    public Restaurant get(int id){
        return restaurantRepository.get(id);
    }

    @CacheEvict(value = "meals", allEntries = true)
    public void delete(int id){
        restaurantRepository.delete(id);
    }

    @CacheEvict(value = "meals", allEntries = true)
    public void update(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    @Cacheable("meals")
    public List<Restaurant> getAll(){
       return restaurantRepository.getAll();
    }

    public Restaurant getWithMeal(int id){
        return restaurantRepository.getWithMeal(id);
    }

    public List<Restaurant> getRestaurantsWithMeals(){
        return restaurantRepository.getAllRestaurantsWithMeals();
    }

    public List<Restaurant> getRestaurantsByDate(LocalDate date){
        return restaurantRepository.getAllRestaurantsByDate(date);
    }


}
