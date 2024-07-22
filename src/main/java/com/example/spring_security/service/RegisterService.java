package com.example.spring_security.service;

import com.example.spring_security.entity.Role;
import com.example.spring_security.entity.User;
import com.example.spring_security.entity.UserRoles;
import com.example.spring_security.repository.RoleRepository;
import com.example.spring_security.repository.UserRepository;
import com.example.spring_security.repository.UserRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRolesRepository userRolesRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public String registerUser(User user) {
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            List<Role> roles = roleRepository.findByNameIn(user.getRoles());
            List<UserRoles> userRolesList = roles.stream().map(role -> UserRoles.builder()
                    .roleId(role.getId()).userId(savedUser.getId()).build()).toList();
            userRolesRepository.saveAll(userRolesList);
            return "user registered successfully";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("error in registering the user");
        }

    }

    public List<User> showUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().peek(user -> {
            List<Long> rolesIdList = userRolesRepository.findByUserId(user.getId()).stream()
                    .map(UserRoles::getRoleId).toList();
            List<Role> roles = roleRepository.findByIdIn(rolesIdList);
            user.setRoles(roles.stream().map(Role::getName).toList());
        }).toList();
    }
}
