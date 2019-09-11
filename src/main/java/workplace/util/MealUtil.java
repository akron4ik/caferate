package workplace.util;


import workplace.model.Meal;
import workplace.model.Restaurant;
import workplace.to.MealTo;

public class MealUtil {

    public static Meal createNewFromTo(MealTo mealTo, Restaurant restaurant) {
        return new Meal(null, mealTo.getName(), mealTo.getDate(), mealTo.getPrice(), restaurant);
    }

    public static MealTo asTo(Meal meal) {
        return new MealTo(meal.getId(), meal.getName(), meal.getDate(), meal.getPrice(), meal.getRestaurant().getId());
    }

    public static Meal updateFromTo(Meal meal, MealTo mealTo) {
        meal.setName(mealTo.getName());
        meal.setPrice(mealTo.getPrice());
        meal.setDate(mealTo.getDate());
        return meal;
    }
}
