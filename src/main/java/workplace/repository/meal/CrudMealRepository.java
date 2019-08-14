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
    @Modifying
    @Transactional
    Meal save(Meal meal);

    @Modifying
    @Transactional
    int deleteMealById(int id);

    Meal getMealById( int id);

    @Query("SELECT m FROM Meal m JOIN FETCH m.restaurant WHERE m.id=:id")
    Meal getMealByIdWithRestaurant(@Param("id")int id);

    List<Meal> getMealsByRestaurantId(/*@Param("restaurant_id")*/ int restaurantId);

    List<Meal> getMealsByDateAndRestaurantId(int restaurantId, LocalDate localDate);


}
