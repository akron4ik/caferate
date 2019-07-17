package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "cafes")
public class Cafe extends AbstractBaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private int rating;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cafe")
    private List<Meal> meals;

    public Cafe() {
    }

    public Cafe (String name, int rating ){
        this(null, name, rating);
    }


    public Cafe(Integer id, String name, int rating) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
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
        return "Cafe{" +
                "id=" + id +
                ", name='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
