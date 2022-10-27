package com.sd.energy.security.repository;

import com.sd.energy.domain.model.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("SELECT r FROM roles r WHERE r.authority = ?1")
    Optional<Role> findByRoleName(String role);
}
