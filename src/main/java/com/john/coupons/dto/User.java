package com.john.coupons.dto;

import com.john.coupons.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Long companyId;
    private Role role;
    transient private String token;


}

