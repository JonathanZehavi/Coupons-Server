package com.john.coupons.converters;

import com.john.coupons.dto.Customer;
import com.john.coupons.entities.CustomerEntity;


public class CustomerEntityConverter {


    public static CustomerEntity from(Customer customer){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customer.getId());
        customerEntity.setUser(UserEntityConverter.from(customer.getUser()));
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setAmountOfChildren(customer.getAmountOfChildren());
        customerEntity.setBirthday(customer.getBirthday());
        customerEntity.setPhoneNumber(customer.getPhoneNumber());
        return customerEntity;
    }

}
