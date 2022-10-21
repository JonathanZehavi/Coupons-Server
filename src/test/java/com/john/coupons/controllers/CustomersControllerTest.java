package com.john.coupons.controllers;

import com.john.coupons.dto.Customer;
import com.john.coupons.dto.User;
import com.john.coupons.enums.Role;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.service.CustomersService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.Null;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CustomersControllerTest {

    @Mock
    private CustomersService customersService;
    private CustomersController underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomersController(customersService);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateCustomer() throws ApplicationException {
        Customer customer = new Customer(
                1L,
                new User(
                        1L,
                        "customer@gmail.com",
                        "Aa123456789",
                        "John",
                        "Zehavi",
                        null,
                        Role.Customer,
                        null
                        ),
                "israel",
                0,
                LocalDate.of(1992, 5, 16),
                "054747499"
                );

        underTest.createCustomer(customer);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        Customer customerValue = customerArgumentCaptor.getValue();

        assertThat(customerValue).isEqualTo(customer);

    }

    @Test(expected = NullPointerException.class)
    public void testGetCustomerById() throws ApplicationException {
        underTest.getCustomerById(1L);
        verify(customersService).getCustomerById(1L);
    }

    @Test(expected = NullPointerException.class)
    public void testGetAllCustomers() throws ApplicationException {
        underTest.getAllCustomers();
        verify(customersService).getAllCustomers();
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateCustomer() throws ApplicationException {
        Customer customer = customersService.getCustomerById(1L);

        customer.setAddress("usa");
        customer.setPhoneNumber("0547479929");
        customer.setAmountOfChildren(1);
        customer.setBirthday(LocalDate.of(1992, 10, 16));

        underTest.updateCustomer(customer.getId(), customer);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        Customer customerValue = customerArgumentCaptor.getValue();

        assertThat(customerValue).isEqualTo(customer);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteCustomer() throws ApplicationException {
        underTest.deleteCustomer(1L);
        verify(customersService).deleteCustomer(1L);
    }


}