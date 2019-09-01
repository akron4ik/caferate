package workplace.repository.restaurant;

import org.springframework.transaction.annotation.Propagation;
import workplace.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant c WHERE c.id=:id")
    int delete(@Param("id") int id);

    Restaurant getRestaurantById(int id);

    @Query("SELECT c FROM Restaurant c")
    List<Restaurant> getAll();

    @EntityGraph(attributePaths = {"meals"})
    @Query("SELECT c FROM Restaurant c WHERE c.id=:id")
     Restaurant getWithMeals(@Param("id") int id);

    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.meals m WHERE m.date=:dateTime")
    List<Restaurant> getRestaurantsByDate(@Param("dateTime") LocalDate localDate);
}
