package com.john.coupons.converters;

import com.john.coupons.dto.Customer;
import com.john.coupons.entities.CustomerEntity;
import lombok.Data;


    public class CustomerDtoConverter {

    public static Customer from(CustomerEntity customerEntity) {
        Customer customer = new Customer();
        customer.setId(customerEntity.getId());
        customer.setUser(UserDtoConverter.from(customerEntity.getUser()));
        customer.setAmountOfChildren(customerEntity.getAmountOfChildren());
        customer.setAddress(customerEntity.getAddress());
        customer.setBirthday(customerEntity.getBirthday());
        customer.setPhoneNumber(customerEntity.getPhoneNumber());

        return customer;
    }

}
