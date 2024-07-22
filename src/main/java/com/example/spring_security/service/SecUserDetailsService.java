package com.example.spring_security.service;

import com.example.spring_security.entity.Role;
import com.example.spring_security.entity.User;
import com.example.spring_security.entity.UserRoles;
import com.example.spring_security.repository.RoleRepository;
import com.example.spring_security.repository.UserRepository;
import com.example.spring_security.repository.UserRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRolesRepository userRolesRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userData = userRepository.findByName(username).orElseThrow(() -> new RuntimeException("Incorrect username"));
        List<UserRoles> userRoles = userRolesRepository.findByUserId(userData.getId());
        List<Long> roleIds = userRoles.stream().map(UserRoles::getRoleId).toList();
        List<Role> roles = roleRepository.findByIdIn(roleIds);
        List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new
                SimpleGrantedAuthority(role.getName())).toList();
        return new org.springframework.security.core.userdetails.User(username, userData.getPassword(), authorities);
    }
}
