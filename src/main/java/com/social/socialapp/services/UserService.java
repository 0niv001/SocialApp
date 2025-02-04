package com.social.socialapp.services;

import com.social.socialapp.entity.UserEntity;
import com.social.socialapp.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@EnableJpaRepositories
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    HttpSession session;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserEntity registerUser(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(hashedPassword);
        userEntity.setRoles("USER");
        return userRepo.save(userEntity);
    }

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }



    public boolean authenticateUser(String username, String password) {
        Optional<UserEntity> user = userRepo.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        List<SimpleGrantedAuthority> authorities = List.of(userEntity.getRoles())
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }

    /*
    public UserEntity getCurrentUser() {
        return (UserEntity) session.getAttribute("user");
    }

     */

    public Optional<UserEntity> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}


// Get role for user - Redundant? all accounts will have role of Users

