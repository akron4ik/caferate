package workplace.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import workplace.HasId;
import java.util.List;
import java.util.Objects;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE )
@Table(name = "restaurants", uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx"))
public class Restaurant extends AbstractBaseEntity implements HasId {

    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 200)
    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    @JsonManagedReference
    private List<Meal> meals;

    public Restaurant() {
    }

    public Restaurant(Restaurant restaurant ){
        this(restaurant.getId(), restaurant.getName());
    }

    public Restaurant(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Restaurant (Integer id, String name, List<Meal> meals){
        super(id);
        this.name = name;
        this.meals = meals;
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

    public boolean isNew() {
        return this.id == null;
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", meals=" + meals +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(meals, that.meals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, meals);
    }
}
