package workplace.repository.user;

import org.springframework.transaction.annotation.Propagation;
import workplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public interface CrudUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    int deleteUserById(int id);

    User getUserByEmail(String email);

    @Query("SELECT u FROM User u")
    List<User> getAll();

}
