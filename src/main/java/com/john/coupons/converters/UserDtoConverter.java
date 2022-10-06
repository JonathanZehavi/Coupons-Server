package com.john.coupons.converters;

import com.john.coupons.dto.User;
import com.john.coupons.entities.UserEntity;
import com.john.coupons.enums.Role;

public class UserDtoConverter {

    public static User from(UserEntity userEntity){
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setFirstname(userEntity.getFirstname());
        user.setLastname(userEntity.getLastname());
        user.setRole(userEntity.getRole());
        if (user.getRole() == Role.Company){
            user.setCompanyId(userEntity.getCompanyId());
        }
        user.setToken(userEntity.getToken());
        return user;
    }


}
