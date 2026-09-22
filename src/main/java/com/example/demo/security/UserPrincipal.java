package com.example.demo.security;

import com.example.demo.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private final UserEntity user;
    private final String role;

    public UserPrincipal(UserEntity user, String role) {
        this.user = user;
        this.role = role;
    }

    public Integer getId() { return user.getId(); }
    public String getFullname() { return user.getFullname(); }
    public String getEmail() { return user.getEmail(); }
    public String getAvatar() { return user.getAvatar(); }
    public String getRole() { return role; }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return List.of(new SimpleGrantedAuthority(role)); }
    @Override public String getPassword() { return user.getPassword(); }
    @Override public String getUsername() { return user.getUsername(); }
    @Override public boolean isEnabled() { return Boolean.TRUE.equals(user.getActive()); }
}
