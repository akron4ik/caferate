package workplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import workplace.model.Meal;
import workplace.model.Restaurant;
import workplace.repository.meal.CrudMealRepository;
import workplace.repository.restaurant.CrudRestaurantRepository;
import workplace.to.MealTo;
import workplace.util.MealUtil;

import java.time.LocalDate;
import java.util.List;

import static workplace.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private static final Sort SORT_BY_DATE = Sort.by(Sort.Order.desc("date"));
    private final CrudMealRepository mealRepository;
    private final CrudRestaurantRepository restaurantRepository;

    @Autowired
    public MealService(CrudMealRepository mealRepository, CrudRestaurantRepository restaurantRepository){
        this.mealRepository = mealRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Meal create(Meal meal){
        Assert.notNull(meal, "meal must not be null");
        return mealRepository.save(meal);
    }

    public void delete(int id){
        checkNotFoundWithId(mealRepository.deleteMealById(id),id);
    }

    public void update(Meal meal){
        Assert.notNull(meal, "meal must not be null");
        checkNotFoundWithId(mealRepository.save(meal), meal.getId());
    }

    public void update(MealTo mealTo){
        Assert.notNull(mealTo, "meal must not be null");
        Meal meal = get(mealTo.getId());
        checkNotFoundWithId(mealRepository.save(MealUtil.updateFromTo(meal, mealTo)), mealTo.getId());
    }

    public Meal get(int id){
        return checkNotFoundWithId(mealRepository.getMealById(id), id);
    }

    public List<Meal> getAllByRestaurantId(int restaurantId){
        return mealRepository.getMealsByRestaurantId(restaurantId, SORT_BY_DATE);
    }

    public List<Meal> getAll(){return mealRepository.getAll();}

    public Meal getMealWithRestaurant(int id){
        return mealRepository.getMealByIdWithRestaurant(id);
    }

    public List<Meal> getMealsByDate(LocalDate localDate, int restaurantId){
       return mealRepository.getMealsByDateAndRestaurantId(localDate, restaurantId);
    }

}
