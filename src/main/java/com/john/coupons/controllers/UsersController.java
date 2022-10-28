package com.john.coupons.controllers;

import com.john.coupons.dto.User;
import com.john.coupons.dto.UserLoginDetails;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDetails userLoginDetails) throws ApplicationException {
        return new ResponseEntity<>(usersService.login(userLoginDetails), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws ApplicationException {
        return new ResponseEntity<>(usersService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) throws ApplicationException {
        return new ResponseEntity<>(usersService.updateUser(id, user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) throws ApplicationException {
        return new ResponseEntity<>(usersService.getUser(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() throws ApplicationException {
        return new ResponseEntity<>(usersService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/isExistByUsername")
    public ResponseEntity<Boolean> isExistByUsername(@RequestParam("username") String username) throws ApplicationException {
        return new ResponseEntity<>(usersService.isExistByUsername(username), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) throws ApplicationException {
        usersService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/byUsername")
    public ResponseEntity<User> findByUsername(@RequestParam("username") String username) throws ApplicationException {
        return new ResponseEntity<>(usersService.findByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/isUserExist/{id}")
    public ResponseEntity<Boolean> isUserExist(@PathVariable("id") Long id) throws ApplicationException {
        return new ResponseEntity<>(usersService.existById(id), HttpStatus.OK);
    }

    @GetMapping("/parameterToSortByAscending")
    public ResponseEntity<List<User>> findUsersWithSortingAsc(@RequestParam("sortAscending") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(usersService.findUsersWithSortingAscending(parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/parameterToSortByDescending")
    public ResponseEntity<List<User>> findUsersWithSortingDesc(@RequestParam("sortDescending") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(usersService.findUsersWithSortingDescending(parameterToSortBy), HttpStatus.OK);
    }

    @GetMapping("/pages/{pageNumber}/{pageSize}")
    public ResponseEntity<List<User>> findUsersWithPagination(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize) throws ApplicationException {
        return new ResponseEntity<>(usersService.findUsersWithPagination(offset, pageSize), HttpStatus.OK);
    }

    @GetMapping("/pageAndSortAscending/{pageNumber}/{pageSize}")
    public ResponseEntity<List<User>> getAllUsersWithPaginationAndSortingAsc(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize, @RequestParam("parameterToSortBy") String parameterToSortBy) throws ApplicationException {
        return new ResponseEntity<>(usersService.findUsersWithPaginationAndSortingAscending(offset, pageSize, parameterToSortBy), HttpStatus.OK);
    }
    @GetMapping("/pageAndSortDescending/{pageNumber}/{pageSize}")
    public ResponseEntity<List<User>> getAllUsersWithPaginationAndSortingDesc(@PathVariable("pageNumber") int offset, @PathVariable("pageSize") int pageSize, @RequestParam("parameterToSortBy") String parameterToSortBy) throws ApplicationException{
        return new ResponseEntity<>(usersService.findUsersWithPaginationAndSortingDescending(offset, pageSize, parameterToSortBy), HttpStatus.OK);
    }


}

