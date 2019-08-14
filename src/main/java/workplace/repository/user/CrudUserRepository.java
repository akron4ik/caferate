package workplace.repository.user;

import workplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    int deleteUserById(int id);

    User getUserByEmail(String email);

    @Query("SELECT u FROM User u")
    List<User> getAll();

}
