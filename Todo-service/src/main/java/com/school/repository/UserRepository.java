package com.school.repository;

import com.school.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    public List<User> findByEnabledTrue();

    @Query("select u from User u where u.username like :username and u.id <> :userId ")
    Optional<User> findByUsernameExept(String username, Long userId);
}
