package com.sd.energy.security.repository;

import com.sd.energy.domain.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM users u WHERE u.username = ?1")
    Optional<User> findByUsername(String username);
}
