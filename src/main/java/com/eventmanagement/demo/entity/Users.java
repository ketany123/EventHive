package com.eventmanagement.demo.entity;

import com.eventmanagement.demo.enums.Role;
import jakarta.persistence.*;
;


@Entity
public class Users {

    @Id
    private String id;
    private String username;
    private String email;
    private String password;

    @Enumerated(value=EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public String getId() {return id;}

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
