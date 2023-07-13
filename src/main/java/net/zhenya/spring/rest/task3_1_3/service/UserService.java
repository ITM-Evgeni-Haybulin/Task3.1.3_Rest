package net.zhenya.spring.rest.task3_1_3.service;

import net.zhenya.spring.rest.task3_1_3.model.Role;
import net.zhenya.spring.rest.task3_1_3.model.User;
import net.zhenya.spring.rest.task3_1_3.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public User findByFirstname(String username) {
        return userRepository.findByFirstname(username);
    }

    public User getUserById(Long id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional // для ленивой загрузки. так как коллекция
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByFirstname(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username  + " not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                mapRolesToAuthority(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthority(Collection<Role> roles) {
       return roles.stream()
               .map(r -> new SimpleGrantedAuthority(r.getName()))
               .collect(Collectors.toList());
    }
}
