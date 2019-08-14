package workplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import workplace.model.Meal;
import workplace.repository.meal.CrudMealRepository;
import workplace.repository.meal.DataJpaMealRepository;

import java.time.LocalDate;
import java.util.List;

import static workplace.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private final DataJpaMealRepository mealRepository;

    @Autowired
    public MealService(DataJpaMealRepository mealRepository){
        this.mealRepository = mealRepository;
    }

    public Meal create(Meal meal){
        Assert.notNull(meal, "user must not be null");
        return mealRepository.save(meal);
    }

    public void delete(int id){
        checkNotFoundWithId(mealRepository.delete(id),id);
    }

    public void update(Meal meal){
        Assert.notNull(meal, "user must not be null");
        checkNotFoundWithId(mealRepository.save(meal), meal.getId());
    }

    public Meal get(int id){
        return checkNotFoundWithId(mealRepository.get(id), id);
    }

    public List<Meal> getAll(int restaurantid){
        return mealRepository.getAll(restaurantid);
    }

    public Meal getMealWithRestaurant(int id){
        return mealRepository.getMealWithRestaurant(id);
    }

    public List<Meal> getMealsByDate(int restaurantId, LocalDate localDate){
       return mealRepository.getMealByDateAndRestaurantId(restaurantId, localDate);
    }

}
