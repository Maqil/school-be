package com.school.security.jwt;


import com.school.entities.User;
import com.school.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        logger.info("load user...");
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            logger.info("user:: {}", user.get().getUsername());
            return getJwtUser(user.get());
        } else {
            logger.info("user not found");
            //throw new UsernameNotFoundException(String.format("User not found with username '%s'.", username));
            return null;
        }
    }

    public JwtUser getJwtUser(User user) {
        return new JwtUser(
                user.getUserID(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().getName().name())),
                user.getEnabled(),
                null
        );
    }
}
