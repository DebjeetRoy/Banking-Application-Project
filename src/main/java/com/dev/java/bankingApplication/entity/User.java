package com.dev.java.bankingApplication.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "USERS_AUTHENTICATION_TBL")
@Data
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true)
        private String username;

        private boolean active;
        private String roles;//ROLE_USER,ROLE_ADMIN

        private String password;

//        @ManyToMany(fetch = FetchType.EAGER)
//        @JoinTable(name = "user_roles",
//                joinColumns = @JoinColumn(name = "user_id"),
//                inverseJoinColumns = @JoinColumn(name = "role_id"))
//        private Set<Role> roles;
}
