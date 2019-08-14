package workplace.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Restaurant create(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    public Restaurant get(int id){
        return restaurantRepository.get(id);
    }

    public void delete(int id){
        restaurantRepository.delete(id);
    }

    public void update(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

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
