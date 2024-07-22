package com.example.spring_security.repository;

import com.example.spring_security.entity.UserRoles;
import com.example.spring_security.entity.UserRoles_PK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles, UserRoles_PK> {
    List<UserRoles> findByUserId(Long userId);
}
