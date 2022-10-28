package com.john.coupons.dto;

import com.john.coupons.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDetails {

    private String username;
    private String password;
    private Role role;

}

