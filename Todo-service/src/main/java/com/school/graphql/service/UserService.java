package com.school.graphql.service;


import com.school.entities.Role;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import com.school.graphql.exception.InvalidCredentialsException;
import com.school.entities.User;
import com.school.repository.UserRepository;
import com.school.security.jwt.JwtTokenUtil;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;


@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GraphQLQuery(name = "user")
    public Optional<User> getUserById(@GraphQLArgument(name = "id") Long id) {
        return userRepository.findById(id);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GraphQLMutation(name = "signup")
    public User signup(@GraphQLNonNull @GraphQLArgument(name = "username") String username,
                       @GraphQLArgument(name = "email") String email,
                       @GraphQLArgument(name = "password") String password,
                       @GraphQLArgument(name = "firstName") String firstName,
                       @GraphQLArgument(name = "lastName") String lastName,
                       @GraphQLArgument(name = "enabled") Boolean enabled,
                       @GraphQLArgument(name = "role") Role role) {
        return userRepository.save(new User(username, email, password, firstName, lastName, enabled, role));
    }


    @GraphQLMutation(name = "signin", description = "signin")
    public String signin(@GraphQLArgument(name = "email") String email,
                         @GraphQLArgument(name = "password") String password) throws InvalidCredentialsException {
//        Optional<User> user = userRepository.findByUsername(username);
        Optional<User> user = userRepository.findByEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(user.get().getRole().getName());
//        System.out.println(user.get().getBeautifyRoleName());
        if (user.isPresent()) {
//            if (encoder.matches(password, user.get().getPassword())) {
            if (user.get().getPassword().equals(password)) {
                logger.info("success...");
                logger.info("sub: {}", user.get().getUsername());
                return jwtTokenUtil.generateToken(user.get().getUsername(), user.get().getRole());
            } else {
                logger.info("Invalid Credentials1");
                throw new InvalidCredentialsException("Invalid Credentials!");
            }
        } else {
            logger.info("Invalid Credentials2");
            throw new InvalidCredentialsException("Invalid Credentials!");
        }
    }
}
