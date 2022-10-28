package com.john.coupons.security;

import com.john.coupons.entities.UserEntity;
import com.john.coupons.enums.Role;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserSecurity implements UserDetails {

    @Autowired
    private static final long serialVersionUID = -6690946490872875352L;
    private Long id;
    private String username;
    private Role role;
    transient private String password;
    private final UserEntity userEntity;
    private Set<GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

