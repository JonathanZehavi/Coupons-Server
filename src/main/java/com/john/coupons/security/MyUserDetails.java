package com.john.coupons.security;

import com.john.coupons.entities.UserEntity;
import com.john.coupons.repositories.UserRepository;
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
    private final UserRepository userRepository; // USERSERVICE

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserEntity userEntity = userRepository.findByUsername(username);

            Set<GrantedAuthority> authorities = new HashSet<>();
            GrantedAuthority role = SecurityUtils.convertToAuthority(userEntity.getRole().name());
            authorities.add(role);

            return UserSecurity.builder()
                    .user(userEntity)
                    .id(userEntity.getId())
                    .username(userEntity.getUsername())
                    .password(userEntity.getPassword())
                    .authorities(authorities)
                    .build();
        } catch (Exception e){
            throw new UsernameNotFoundException(username);
        }
    }
}

