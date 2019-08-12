package workplace.repository.meal;

import workplace.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaMealRepository {

    @Autowired
    CrudMealRepository crudMealRepository;

    public Meal save(Meal meal){
        return crudMealRepository.save(meal);
    }

    public boolean delete(int id){
        return crudMealRepository.delete(id) != 0;
    }

    public Meal get(int id){
        return crudMealRepository.get(id);
    }

    public Meal getMealWithRestaurant(int id){
        return crudMealRepository.getMealByIdWithRestaurant(id);
    }
    public List<Meal> getAll(){
        return crudMealRepository.getAll();
    }

    public List<Meal> getAllMealByDate(int restaurantId, LocalDate localDate){
        return crudMealRepository.getAllMealsByDateAndRestaurantId(restaurantId, localDate);
    }

    public List<Meal> getAllMealByRestaurantId(int id) {
        return crudMealRepository.getAllMealByRestaurantId(id);
    }

}
