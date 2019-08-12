package workplace.repository.voice;

import workplace.model.Voice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoiceRepository extends JpaRepository<Voice, Integer> {

    @Override
    @Transactional
    Voice save(Voice voice);

    @Modifying
    @Transactional
    @Query("DELETE FROM Voice v WHERE v.id=:id")
    int delete(@Param("id") int id, int userId);

    @Query("SELECT v FROM Voice v WHERE v.id=:id")
    Voice get(@Param("id") int id);

    @Query("SELECT v FROM Voice v")
    List<Voice> getAll();

    Voice getVoiceByIdAndUserId(int id, int userId);

    List<Voice> getAllUserVoices(int userId);

    @Query("SELECT COUNT(v) FROM Voice v WHERE v.restaurant.id=:id AND v.localDateTime=:date")
    int getCountVoicesByDate(@Param("id") int restaurantId, @Param("date") LocalDate date);
}
