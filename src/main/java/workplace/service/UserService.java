package workplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import workplace.model.User;
import workplace.repository.user.DataJpaUserRepository;
import workplace.util.ValidationUtil;

import java.util.List;

import static workplace.util.ValidationUtil.checkNotFound;
import static workplace.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserService {

    private final DataJpaUserRepository userRepository;

    @Autowired
    public UserService(DataJpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user){
        Assert.notNull(user, "user must not be null");
        return userRepository.save(user);
    }

    public User get(int id){
         return checkNotFoundWithId(userRepository.get(id), id);
    }

    public User getByEmail(String email){
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user){
        Assert.notNull(user, "user must not be null");
        userRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id){
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    @Cacheable("users")
    public List<User> getAll(){
        return userRepository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        userRepository.save(user);  // !! need only for JDBC implementation
    }
}
