package workplace.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "meals", uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id", "name"}, name = "meals_unique_name_idx"))
public class Meal extends AbstractBaseEntity {

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "date_time")
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "price")
    @NotNull
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public Meal() {
    }


    public Meal(Integer id, String name, LocalDateTime dateTime, double price, Restaurant restaurant) {
        super(id);
        this.name = name;
        this.dateTime = dateTime;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Meal(Meal meal){
        this(meal.getId(), meal.getName(), meal.getDateTime(), meal.getPrice(),  meal.getRestaurant());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isNew(){
        return this.id == null;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", price=" + price +
                ", restaurant=" + restaurant +
                '}';
    }
}
