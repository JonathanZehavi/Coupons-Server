package com.john.coupons.repositories;

import com.john.coupons.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsById(Long id);

    @Query("SELECT CASE WHEN COUNT(u.username) = 1 THEN true ELSE false END from UserEntity u where u.username = :username ")
    Boolean isExistByUsername(@Param("username") String username);

//    UserEntity findById(Long id);

    @Query(value = "SELECT u From UserEntity u WHERE u.username = :username ")
    UserEntity findByUsername(@Param("username") String username);


}

