package workplace.repository.user;

import org.springframework.data.domain.Sort;
import workplace.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaUserRepository {
    private static final Sort SORT_BY_DATE = new Sort(Sort.Direction.DESC, "registered");
    private static final Sort SORT_BY_ID = new Sort(Sort.Direction.ASC, "id");

    @Autowired
    CrudUserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean delete(int id) {
        return userRepository.deleteUserById(id) != 0;
    }

    public User get(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAll() {
        return userRepository.findAll(SORT_BY_DATE);
    }

    public User getByEmail(String email){
        return userRepository.getUserByEmail(email);
    }
}
