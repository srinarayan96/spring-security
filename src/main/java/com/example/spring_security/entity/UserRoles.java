package com.example.spring_security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_Roles")
@Data
@IdClass(UserRoles_PK.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoles {
    @Id
    @Column(name = "User_id")
    private Long userId;
    @Id
    @Column(name = "Role_id")
    private Long roleId;

}
