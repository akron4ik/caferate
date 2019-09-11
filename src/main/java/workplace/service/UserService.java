package workplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import workplace.AuthorizedUser;
import workplace.model.User;
import workplace.repository.user.CrudUserRepository;
import workplace.to.UserTo;
import workplace.util.UserUtil;

import java.util.List;

import static workplace.util.UserUtil.prepareToSave;
import static workplace.util.ValidationUtil.checkNotFound;
import static workplace.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {
    private static final Sort SORT = new Sort(Sort.Direction.ASC, "name");
    private final CrudUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(CrudUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user){
        Assert.notNull(user, "user must not be null");
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }

    public User get(int id){
         return checkNotFoundWithId(userRepository.findById(id).orElse(null), id);
    }

    public User getByEmail(String email){
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getUserByEmail(email), "email=" + email);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user){
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(userRepository.save(user), user.getId());
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void update(UserTo userTo) {
        User user = get(userTo.id());
        userRepository.save(prepareToSave(UserUtil.updateFromTo(user, userTo), passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id){
        checkNotFoundWithId(userRepository.deleteUserById(id), id);
    }

    @Cacheable("users")
    public List<User> getAll(){
        return userRepository.findAll(SORT);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
