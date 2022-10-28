package com.john.coupons.security;

import com.john.coupons.converters.UserEntityConverter;
import com.john.coupons.dto.User;
import com.john.coupons.entities.UserEntity;
import com.john.coupons.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private final UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {

            User user = usersService.findByUsername(username);
            UserEntity userEntity = UserEntityConverter.from(user);

            Set<GrantedAuthority> authorities = new HashSet<>();
            GrantedAuthority role = SecurityUtils.convertToAuthority(user.getRole().name());
            authorities.add(role);

            return UserSecurity.builder()
                    .userEntity(userEntity)
                    .id(user.getId())
                    .username(userEntity.getUsername())
                    .password(userEntity.getPassword())
                    .role(userEntity.getRole())
                    .authorities(authorities)
                    .build();
        } catch (Exception e){
            throw new UsernameNotFoundException(username);
        }
    }
}

