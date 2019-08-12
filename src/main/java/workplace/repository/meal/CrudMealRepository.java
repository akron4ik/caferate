package workplace.repository.meal;

import workplace.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Override
    @Transactional
    Meal save(Meal meal);

    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT m FROM Meal m WHERE m.id=:id")
    Meal get(@Param("id") int id);

    @Query("SELECT m FROM Meal m JOIN FETCH m.restaurant WHERE m.id=:id")
    Meal getMealByIdWithRestaurant(@Param("id")int id);

    @Query("SELECT m FROM Meal m")
    List<Meal> getAll();

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restaurant_id")
    List<Meal> getAllMealByRestaurantId(@Param("restaurant_id") int id);

    List<Meal> getAllMealsByDateAndRestaurantId(int restaurantId, LocalDate localDate);


}
