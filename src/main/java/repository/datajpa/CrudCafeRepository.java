package repository.datajpa;

import model.Cafe;
import model.Meal;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
public interface CrudCafeRepository extends JpaRepository<Cafe, Integer> {

    @Override
    @Transactional
    Cafe save(Cafe cafe);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cafe c WHERE c.id=:id")
    int delete(int id);

    @Query("SELECT c FROM Cafe c WHERE c.id=:id")
    Cafe get(int id);

    @Query("SELECT c FROM Cafe c")
    List<Cafe> getAll();

    @EntityGraph(attributePaths = {"meals", "roles"})
    @Query("SELECT c FROM Cafe c WHERE c.id=?1")
    Cafe getWithMeals(int id);
}
