package Util;

import model.Meal;
import model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import repository.datajpa.DataJpaMealRepository;
import repository.datajpa.DataJpaRestaurantRepository;
import repository.datajpa.DataJpaUserRepository;
import repository.datajpa.DataJpaVoiceRepository;
import to.RestaurantMenu;

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
