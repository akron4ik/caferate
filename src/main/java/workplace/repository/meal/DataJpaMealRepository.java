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
        return crudMealRepository.deleteMealById(id) != 0;
    }

    public Meal get(int id){
        return crudMealRepository.getMealById(id);
    }

    public Meal getMealWithRestaurant(int id){
        return crudMealRepository.getMealByIdWithRestaurant(id);
    }

    public List<Meal> getAllByRestaurantId(int id){
        return crudMealRepository.getMealsByRestaurantId(id);
    }

    public List<Meal> getMealByDateAndRestaurantId(LocalDate localDate, int restaurantId){
        return crudMealRepository.getMealsByDateAndRestaurantId( localDate, restaurantId);
    }

    public List<Meal> getAll(){ return crudMealRepository.getAll();}


}
