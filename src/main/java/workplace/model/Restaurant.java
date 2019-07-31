package workplace.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private Integer rating;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Meal> meals;

    public Restaurant() {
    }

    public Restaurant(String name, Integer rating ){
        this(null, name, rating);
    }

    public Restaurant(String name ){
        this(null, name, null);
    }


    public Restaurant(Integer id, String name, Integer rating) {
        super(id);
        this.description = name;
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public boolean isNew() {
        return this.id == null;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
