package workplace.util;

import workplace.model.Meal;
import workplace.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import workplace.repository.meal.DataJpaMealRepository;
import workplace.repository.restaurant.DataJpaRestaurantRepository;
import workplace.repository.user.DataJpaUserRepository;
import workplace.repository.voice.DataJpaVoiceRepository;
import workplace.to.RestaurantMenu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeRestaurantMenu {
    @Autowired
    DataJpaRestaurantRepository restaurantRepository;
    @Autowired
    DataJpaMealRepository mealRepository;
    @Autowired
    DataJpaUserRepository userRepository;
    @Autowired
    DataJpaVoiceRepository voiceRepository;

    public RestaurantMenu getMenuByRestaurantId(int id){
        Restaurant restaurant = restaurantRepository.get(id);
        List<Meal> meals = getTodaysMeals(mealRepository.getAllMealByRestaurantId(restaurant.getId()));
        Map<String, Double> mealAndPrice = new HashMap<>();
        for (Meal m: meals) {
            mealAndPrice.put(m.getName(),m.getPrice());
        }
        return new RestaurantMenu(restaurant.getDescription(), mealAndPrice, LocalDate.now());
    }

    public List<Meal> getTodaysMeals(List<Meal> meals){
        List<Meal> newMeals = new ArrayList<>();
        meals.stream().filter(m -> m.getDateTime().toLocalDate()==LocalDate.now()).forEach(newMeals::add);
        return newMeals;
    }
}
