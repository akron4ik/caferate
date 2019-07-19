package repository.datajpa;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaUserRepository {
    @Autowired
    CrudUserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean delete(int id) {
        return userRepository.delete(id) != 0;
    }

    public User get(int id) {
        return userRepository.get(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }
}
