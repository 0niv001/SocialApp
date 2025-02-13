package com.social.socialapp.services;

import com.social.socialapp.entity.UserEntity;
import com.social.socialapp.repository.UserRepo;
import com.social.socialapp.ui.UserView;
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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Service
@EnableJpaRepositories
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserEntity registerUser(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(hashedPassword);
        userEntity.setRoles("ROLE_USER");
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

        //List<SimpleGrantedAuthority> authorities = Stream.of(userEntity.getRoles()).map
        // (SimpleGrantedAuthority::new).toList();

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                //authorities,
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
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

