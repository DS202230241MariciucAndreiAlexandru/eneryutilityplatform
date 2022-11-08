package com.sd.energy.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity(name = "roles")
@Data
public class Role implements GrantedAuthority {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String authority;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Set<User> users = new HashSet<>();
}
