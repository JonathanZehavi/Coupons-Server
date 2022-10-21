package com.john.coupons.controllers;

import com.john.coupons.dto.User;
import com.john.coupons.enums.Role;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.service.UsersService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {

    @Mock
    private UsersService usersService;
    private UsersController underTest;

    @BeforeEach
    void setUp() {
        underTest = new UsersController(usersService);
    }


    @Test(expected = NullPointerException.class)
    @Order(1)
    public void testCreateUser() throws ApplicationException {
        //given
        User user = new User(
                1L,
                "yonatan@gmail.com",
                "Aa123456789",
                "Jonathan",
                "Zehavi",
                null,
                Role.Customer,
                null
        );
        //when
        underTest.createUser(user);

        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(usersService).createUser(userArgumentCaptor.capture());

        User userValue = userArgumentCaptor.getValue();

        assertThat(userValue).isEqualTo(user);
    }


    @Test(expected = NullPointerException.class)
    @Order(2)
    public void testGetUser() throws ApplicationException {

        underTest.getUser(1L);
        verify(usersService).getUser(1L);
    }

    @Test(expected = NullPointerException.class)
    @Order(3)
    public void testGetAllUsers() throws ApplicationException {
        // when
        underTest.getAllUsers();
        // then
        verify(usersService).getAllUsers();
    }

    @Test(expected = NullPointerException.class)
    @Order(4)
    public void testUpdateUser() throws ApplicationException {

        User user = usersService.getUser(1L);

        user.setFirstname("John");
        user.setLastname("Zehavi");
        user.setUsername("yonatan@gmail.com");
        user.setPassword("Aa123456789");
        user.setRole(Role.Customer);

        underTest.updateUser(user.getId(), user);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(usersService).updateUser(user.getId(),userArgumentCaptor.capture());

        User userValue = userArgumentCaptor.getValue();

        assertThat(userValue).isEqualTo(user);

    }

    @Test(expected = NullPointerException.class)
    @Order(5)
    public void testDeleteUser() throws ApplicationException {
        underTest.deleteUser(1L);
        verify(usersService).deleteUser(1L);
    }

}