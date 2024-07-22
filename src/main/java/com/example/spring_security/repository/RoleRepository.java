package com.example.spring_security.repository;

import com.example.spring_security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByNameIn(List<String> names);
    List<Role> findByIdIn(List<Long> ids);
}
