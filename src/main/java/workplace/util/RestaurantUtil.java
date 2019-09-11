package workplace.util;

import workplace.model.Restaurant;
import workplace.to.RestaurantTo;

public class RestaurantUtil {

    public static RestaurantTo asTo (Restaurant restaurant){
        return new RestaurantTo(restaurant.getId(), restaurant.getName());
    }

}
