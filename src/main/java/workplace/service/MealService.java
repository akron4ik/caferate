package workplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import workplace.model.Meal;
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

    @CacheEvict(value = "meals", allEntries = true)
    public Meal create(Meal meal){
        Assert.notNull(meal, "user must not be null");
        return mealRepository.save(meal);
    }

    @CacheEvict(value = "meals", allEntries = true)
    public void delete(int id){
        checkNotFoundWithId(mealRepository.delete(id),id);
    }

    @CacheEvict(value = "meals", allEntries = true)
    public void update(Meal meal){
        Assert.notNull(meal, "user must not be null");
        checkNotFoundWithId(mealRepository.save(meal), meal.getId());
    }

    @Cacheable("meals")
    public Meal get(int id){
        return checkNotFoundWithId(mealRepository.get(id), id);
    }

    @Cacheable("meals")
    public List<Meal> getAllByRestaurantId(int restaurantId){
        return mealRepository.getAllByRestaurantId(restaurantId);
    }

    @Cacheable("meals")
    public List<Meal> getAll(){return mealRepository.getAll();}

    public Meal getMealWithRestaurant(int id){
        return mealRepository.getMealWithRestaurant(id);
    }

    public List<Meal> getMealsByDate(LocalDate localDate, int restaurantId){
       return mealRepository.getMealByDateAndRestaurantId(localDate, restaurantId);
    }

}
