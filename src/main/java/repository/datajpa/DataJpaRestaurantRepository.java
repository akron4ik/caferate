package repository.datajpa;

import model.Restaurant;
import model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        return crudRepository.get(id);
    }

    public List<Restaurant> getAll() {
        return crudRepository.getAll();
    }
    public Restaurant getWithMeal(int id){
        return crudRepository.getWithMeals(id);
    }

    public List<Restaurant> getAllRestaurantsWithMeals(){
        return crudRepository.getAllRestaurantsWithMeals();
    }
}
