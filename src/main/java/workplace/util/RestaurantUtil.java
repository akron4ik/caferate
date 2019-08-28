package workplace.util;


import org.springframework.beans.factory.annotation.Autowired;
import workplace.model.Restaurant;
import workplace.service.VoiceService;
import workplace.to.RestaurantTo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RestaurantUtil {
    @Autowired
    private static VoiceService voiceService;

    public static Restaurant createNewFromTo(RestaurantTo restaurantTo) {
        return new Restaurant(null, restaurantTo.getName(), restaurantTo.getMeals());
    }

    public static RestaurantTo asTo(Restaurant restaurant) {
        int rating = voiceService.getRating(restaurant.getId(), LocalDate.now());
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getMeals(), rating);
    }

    public static Restaurant updateFromTo(Restaurant restaurant, RestaurantTo restaurantTo) {
        restaurant.setName(restaurantTo.getName());
        restaurant.setMeals(restaurantTo.getMeals());
        return restaurant;
    }

    public static RestaurantTo restaurantToWithRating(Restaurant restaurant, LocalDate localDate){
        Integer rating = voiceService.getRating(restaurant.getId(), localDate);
        RestaurantTo restaurantTo =  new RestaurantTo(restaurant.getId(), restaurant.getName() , restaurant.getMeals(), rating);
       return restaurantTo;

    }
    public static List< RestaurantTo> AllToWithRating(List<Restaurant> restaurants, LocalDate localDate){
        List<RestaurantTo> allWithRating = new ArrayList<>();
        for (Restaurant r: restaurants) {
            Integer rating = voiceService.getRating(r.getId(), localDate);
            RestaurantTo restaurantTo =  new RestaurantTo(r.getId(), r.getName() , r.getMeals(), rating);
            allWithRating.add(restaurantTo);
        }
        return allWithRating;


    }
}
