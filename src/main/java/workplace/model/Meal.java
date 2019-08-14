package workplace.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import org.hibernate.annotations.Cache;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "meals", uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id", "name"}, name = "meals_unique_name_idx"))
public class Meal extends AbstractBaseEntity {

    @Size(min = 2, max = 200)
    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "date_time", nullable = false, columnDefinition = "DATE DEFAULT now()")
    @NotNull
    private LocalDate date;

    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 10000)
    @NotNull
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Nullable
    private Restaurant restaurant;

    public Meal() {
    }


    public Meal(Integer id, String name, LocalDate date, double price, Restaurant restaurant) {
        super(id);
        this.name = name;
        this.date = date;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Meal(Meal meal){
        this(meal.getId(), meal.getName(), meal.getDate(), meal.getPrice(),  meal.getRestaurant());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate dateTime) {
        this.date = date;
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
                ", date=" + date +
                ", price=" + price +
                ", id=" + id +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Double.compare(meal.price, price) == 0 &&
                Objects.equals(name, meal.name) &&
                Objects.equals(date, meal.date) &&
                Objects.equals(restaurant, meal.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, price, restaurant);
    }
}
