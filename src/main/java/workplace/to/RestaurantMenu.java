package workplace.to;

import java.time.LocalDate;
import java.util.Map;

public class RestaurantMenu {
    String restaurantName;
    Map<String, Double> menu;
    LocalDate localDate;

    public RestaurantMenu() {
    }

    public RestaurantMenu(String restaurantName, Map<String, Double> menu, LocalDate localDate) {
        this.restaurantName = restaurantName;
        this.menu = menu;
        this.localDate = localDate;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurant(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Map<String, Double> getMenu() {
        return menu;
    }

    public void setMenu(Map<String, Double> menu) {
        this.menu = menu;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }




}
