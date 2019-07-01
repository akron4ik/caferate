package repository.jpa;

import model.User;
import org.springframework.stereotype.Repository;
import repository.UserRepository;

import java.util.List;

@Repository
public class UserRepositoryJpa implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
