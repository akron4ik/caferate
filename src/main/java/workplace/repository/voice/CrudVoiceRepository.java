package workplace.repository.voice;

import workplace.model.Voice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoiceRepository extends JpaRepository<Voice, Integer> {

    Voice getVoiceByIdAndUser_Id(int id, int userId);


    @Transactional
    int deleteVoiceByIdAndUser_id(int id, int userId);



    List<Voice> getAllByUser_Id(int userId);

    @Query("SELECT COUNT(v) FROM Voice v WHERE v.restaurant.id=:id AND v.localDate=:date_time")
    int getCountRestaurantVoicesByDate(@Param("id") int restaurantId, @Param("date_time") LocalDate date);
}
