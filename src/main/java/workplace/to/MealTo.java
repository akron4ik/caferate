package workplace.to;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

public class MealTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Size(min = 2, max = 225)
    @NotBlank
    @SafeHtml
    private String name;

    @Range(min = 10, max = 50000)
    private double price;

    private int restaurantId;

    @NotNull
    private LocalDate date = LocalDate.now();

    public MealTo() {
    }

    public MealTo(Integer id, @Size(min = 2, max = 225) @NotBlank String name, @NotNull LocalDate date, @Range(min = 10, max = 50000) double price, int restaurantId) {
        super(id);
        this.name = name;
        this.date = date;
        this.price = price;
        this.restaurantId = restaurantId;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurant) {
        this.restaurantId = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", restaurantId=" + restaurantId +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
