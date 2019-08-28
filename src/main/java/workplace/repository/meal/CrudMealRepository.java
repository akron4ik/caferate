package workplace.repository.meal;

import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import workplace.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Override
    @Modifying
    @Transactional
    Meal save(Meal meal);

    @Modifying
    @Transactional
    int deleteMealById(int id);

    @Query("SELECT m FROM Meal m")
    List<Meal> getAll();

    Meal getMealById( int id);

    @Query("SELECT m FROM Meal m JOIN FETCH m.restaurant WHERE m.id=:id")
    Meal getMealByIdWithRestaurant(@Param("id")int id);

    List<Meal> getMealsByRestaurantId(int restaurantId, Sort sort);

    List<Meal> getMealsByDateAndRestaurantId(@NotNull LocalDate date, Integer restaurant_id);


}
