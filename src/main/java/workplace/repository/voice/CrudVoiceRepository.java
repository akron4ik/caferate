package workplace.repository.voice;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import workplace.model.Voice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface CrudVoiceRepository extends JpaRepository<Voice, Integer> {

    Voice getVoiceByIdAndUser_Id(int id, int userId);

    @Transactional
    int deleteVoiceByIdAndUser_id(int id, int userId);

    List<Voice> getAllByUser_Id(int userId);

}
