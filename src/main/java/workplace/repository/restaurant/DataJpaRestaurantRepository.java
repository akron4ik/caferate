package workplace.repository.restaurant;

import workplace.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaRestaurantRepository {

   @Autowired
   private CrudRestaurantRepository crudRepository;


    public Restaurant save(Restaurant restaurant) {
        return crudRepository.save(restaurant);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public Restaurant get(int id) {
        return crudRepository.getRestaurantById(id);
    }

    public List<Restaurant> getAll() {
        return crudRepository.getAll();
    }

    public Restaurant getWithMeal(int id){
        return crudRepository.getWithMeals(id);
    }

    public List<Restaurant> getAllRestaurantsByDate(LocalDate localDate){
        return crudRepository.getRestaurantsByDate(localDate);
    }

    public List<Restaurant> getAllRestaurantsWithMeals(){
        return crudRepository.getRestaurantsWithMeals();
    }
}
