package com.john.coupons.controllers;

import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.repositories.UserRepository;
import com.john.coupons.service.UsersService;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UsersControllerTest {

    @Autowired
    private UsersService usersService;
    private UserRepository userRepository;
    private UsersController usersController;

    @Test(expected = ApplicationException.class)
    public void testLogin() throws ApplicationException {

    }

    @Test(expected = ApplicationException.class)
    public void testCreateUser() throws ApplicationException {

    }

    @Test(expected = ApplicationException.class)
    public void testUpdateUser() throws ApplicationException {
    }

    @Test(expected = ApplicationException.class)
    public void testGetUser() throws ApplicationException {
    }

    @Test(expected = ApplicationException.class)
    public void testFindAll() throws ApplicationException {
    }

    @Test(expected = ApplicationException.class)
    public void testIsExistByUsername() throws ApplicationException {
    }

    @Test(expected = ApplicationException.class)
    public void testDeleteUser() throws ApplicationException {
    }

    @Test(expected = ApplicationException.class)
    public void testFindByUsername() throws ApplicationException {
    }

    @Test(expected = ApplicationException.class)
    public void testIsUserExist() throws ApplicationException {
    }

    @Test(expected = ApplicationException.class)
    public void testFindUsersWithSorting() throws ApplicationException {
    }

    @Test(expected = ApplicationException.class)
    public void testFindUsersWithPagination() throws ApplicationException {
    }

    @Test(expected = ApplicationException.class)
    public void testGetAllUsersWithPaginationAndSorting() throws ApplicationException {
    }
}