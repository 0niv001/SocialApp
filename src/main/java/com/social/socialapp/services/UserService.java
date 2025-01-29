package com.social.socialapp.services;
import com.social.socialapp.entity.UserEntity;
import com.social.socialapp.repository.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Service
@EnableJpaRepositories
public class UserService implements UserDetailsService {

   private final UserRepo userRepo;
   private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

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

}
