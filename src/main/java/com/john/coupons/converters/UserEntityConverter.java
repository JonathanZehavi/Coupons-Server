package com.john.coupons.converters;

import com.john.coupons.dto.User;
import com.john.coupons.entities.UserEntity;
import com.john.coupons.enums.Role;

public class UserEntityConverter {

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setFirstname(user.getFirstname());
        userEntity.setLastname(user.getLastname());
        userEntity.setRole(user.getRole());
        userEntity.setToken(user.getToken());
        if (user.getRole() == Role.Company) {
            userEntity.setCompanyId(user.getCompanyId());
        }
        return userEntity;
    }



}
