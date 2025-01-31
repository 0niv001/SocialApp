package com.social.socialapp.services;

import com.social.socialapp.entity.UserEntity;
import com.social.socialapp.repository.UserRepo;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        // Get role for user - Redundant? all accounts will have role of Users

        /*List<SimpleGrantedAuthority> authorities = List.of(userEntity.getRoles().split(","))
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.trim()))
                .toList();
         */

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                List.of(new SimpleGrantedAuthority(userEntity.getRoles()))
        );
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
