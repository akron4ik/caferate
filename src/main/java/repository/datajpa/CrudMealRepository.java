package repository.datajpa;

import model.Meal;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

    @Query("SELECT m FROM Meal m")
    List<Meal> getAll();

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restaurant_id ORDER BY date_time")
    List<Meal> getAllMealByRestaurantId(@Param("restaurant_id") int id);


}
