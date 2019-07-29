package repository.datajpa;

import model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public List<Meal> getAll(){
        return crudMealRepository.getAll();
    }

    public List<Meal> getAllMealByRestaurantId(int id) {
        return crudMealRepository.getAllMealByRestaurantId(id);
    }

}
