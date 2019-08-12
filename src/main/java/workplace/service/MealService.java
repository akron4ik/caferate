package workplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workplace.model.Meal;
import workplace.repository.meal.CrudMealRepository;

@Service
public class MealService {
    private final CrudMealRepository mealRepository;

    @Autowired
    public MealService(CrudMealRepository mealRepository){
        this.mealRepository = mealRepository;
    }

    public Meal create(Meal meal){
        return mealRepository.save(meal);
    }

}
