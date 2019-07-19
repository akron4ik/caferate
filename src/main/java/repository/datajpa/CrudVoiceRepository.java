package repository.datajpa;

import model.Voice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoiceRepository extends JpaRepository<Voice, Integer> {

    @Override
    @Transactional
    Voice save(Voice voice);

    @Modifying
    @Transactional
    @Query("DELETE FROM Voice v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT v FROM Voice v WHERE v.id=:id")
    Voice get(@Param("id") int id);

    @Query("SELECT v FROM Voice v")
    List<Voice> getAll();
}
