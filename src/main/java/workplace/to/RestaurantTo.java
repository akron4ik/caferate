package workplace.to;


import org.hibernate.validator.constraints.SafeHtml;
import workplace.model.Meal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class RestaurantTo extends BaseTo implements Serializable {

    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml
    String name;

    List<Meal> meals;

    Integer rating;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, List<Meal> meals, Integer rating) {
        super(id);
        this.name = name;
        this.meals = meals;
        this.rating = rating;



    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "name='" + name + '\'' +
                ", meals=" + meals +
                '}';
    }
}
