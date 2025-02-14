package com.social.socialapp.services;

import com.social.socialapp.entity.UserEntity;
import com.social.socialapp.repository.UserRepo;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@EnableJpaRepositories
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    //Adds new user credentials to the DB
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


    //Checks user credentials
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


        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public Optional<String> findProfileByUsername(String username) {
        if (username.equalsIgnoreCase(username)) {
            return Optional.of(username+"/profile");
        }
        return Optional.empty();
    }

    public List<UserEntity> searchUserByUsername(String username) {
        return userRepo.findByUsernameContaining(username);
    }
}



