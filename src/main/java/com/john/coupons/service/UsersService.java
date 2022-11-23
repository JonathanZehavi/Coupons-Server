package com.john.coupons.service;

import com.john.coupons.converters.UserDtoConverter;
import com.john.coupons.converters.UserEntityConverter;
import com.john.coupons.dto.User;
import com.john.coupons.dto.UserLoginDetails;
import com.john.coupons.entities.UserEntity;
import com.john.coupons.enums.ErrorType;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.repositories.UserRepository;
import com.john.coupons.security.JWTUtils;
import com.john.coupons.security.UserSecurity;
import com.john.coupons.validations.UserValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final UserRepository userRepository;
    private final UserValidations userValidations;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UsersService(UserRepository userRepository, UserValidations userValidations, AuthenticationManager authenticationManager, JWTUtils jwtUtils, PasswordEncoder passwordEncoder, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userValidations = userValidations;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User login(UserLoginDetails userLoginDetails) throws ApplicationException {
        if (!userRepository.isExistByUsername(userLoginDetails.getUsername())) {
            throw new ApplicationException(ErrorType.USER_DOES_NOT_EXIST);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDetails.getUsername(), userLoginDetails.getPassword())
        );
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userSecurity);
        UserEntity userEntity = userSecurity.getUserEntity();
        userEntity.setId(userSecurity.getId());
        userEntity.setToken(jwt);
        return UserDtoConverter.from(userEntity);
    }



    public User createUser(User user) throws ApplicationException {
        userValidations.validateUser(user);
        UserEntity userEntity = UserEntityConverter.from(user);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity = userRepository.save(userEntity);
        return UserDtoConverter.from(userEntity);
    }

    public User updateUser(Long id, User user) throws ApplicationException {
        userValidations.validateUser(user);
        UserEntity userEntity = UserEntityConverter.from(user);
        userEntity.setId(id);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity = userRepository.save(userEntity);
        return UserDtoConverter.from(userEntity);
    }

    public User getUser(Long id) throws ApplicationException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new ApplicationException(ErrorType.ID_DOES_NOT_EXIST)
        );
        return UserDtoConverter.from(userEntity);
    }

    public List<User> getAllUsers() throws ApplicationException {
        List<UserEntity> userEntityList = userRepository.findAll();
        if (userEntityList.isEmpty()) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR);
        }
        return userEntityList.stream().map(UserDtoConverter::from).collect(Collectors.toList());

    }

    public Boolean isExistByUsername(String username) throws ApplicationException {
        if (username == null) {
            throw new ApplicationException(ErrorType.INVALID_EMAIL);
        }
        return userRepository.isExistByUsername(username);
    }

    public void deleteUser(Long id) throws ApplicationException {
        if (id == null || id <= 0) {
            throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST);
        }
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) throws ApplicationException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity.getUsername() == null) {
            throw new ApplicationException(ErrorType.USER_DOES_NOT_EXIST);
        }
        return UserDtoConverter.from(userEntity);
    }

    public boolean existById(Long id) throws ApplicationException {
        if (id == null) {
            throw new ApplicationException(ErrorType.INVALID_ID);
        }
        return userRepository.existsById(id);
    }

    public List<User> findUsersWithSortingAscending(String parameterToSortBy) throws ApplicationException {
        List<UserEntity> userEntityList = userRepository.findAll(Sort.by(Sort.Direction.ASC, parameterToSortBy));
        if (userEntityList.isEmpty()) {
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return userEntityList.stream().map(UserDtoConverter::from).collect(Collectors.toList());
    }

    public List<User> findUsersWithSortingDescending(String parameterToSortBy) throws ApplicationException {
        List<UserEntity> userEntityList = userRepository.findAll(Sort.by(Sort.Direction.DESC, parameterToSortBy));
        if (userEntityList.isEmpty()) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR);
        }
        return userEntityList.stream().map(UserDtoConverter::from).collect(Collectors.toList());
    }

    public List<User> findUsersWithPagination(int offset, int pageSize) throws ApplicationException {
        Page<UserEntity> usersPagination = userRepository.findAll(PageRequest.of(offset, pageSize));
        if (usersPagination.isEmpty()) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR);
        }
        return usersPagination.stream().map(UserDtoConverter::from).collect(Collectors.toList());
    }

    public List<User> findUsersWithPaginationAndSortingAscending(int offset, int pageSize, String parameterToSortBy) throws ApplicationException {
        Page<UserEntity> userEntityPage = userRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, parameterToSortBy)));
        if (userEntityPage.isEmpty()) {
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return userEntityPage.stream().map(UserDtoConverter::from).collect(Collectors.toList());
    }

    public List<User> findUsersWithPaginationAndSortingDescending(int offset, int pageSize, String parameterToSortBy) throws ApplicationException {
        Page<UserEntity> userEntityPage = userRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, parameterToSortBy)));
        if (userEntityPage.isEmpty()){
            throw new ApplicationException(ErrorType.EMPTY_LIST);
        }
        return userEntityPage.stream().map(UserDtoConverter::from).collect(Collectors.toList());
    }

}

