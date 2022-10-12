package com.john.coupons.converters;

import com.john.coupons.dto.Customer;
import com.john.coupons.dto.Purchase;
import com.john.coupons.entities.CustomerEntity;
import lombok.Data;

import java.util.stream.Collectors;

@Data
    public class CustomerDtoConverter {

    public static Customer from(CustomerEntity customerEntity) {
        Customer customer = new Customer();
        customer.setId(customerEntity.getId());
        customer.setUser(UserDtoConverter.from(customerEntity.getUser()));
        customer.setAmountOfChildren(customerEntity.getAmountOfChildren());
        customer.setAddress(customerEntity.getAddress());
        customer.setBirthday(customerEntity.getBirthday());
        customer.setPhoneNumber(customerEntity.getPhoneNumber());
        if (customer.getPurchases() != null){
        customer.setPurchases(customerEntity.getPurchases()
                .stream()
                .map(purchaseEntity -> {
                    Purchase purchase = new Purchase();
                    purchase.setId(purchaseEntity.getId());
                    purchase.setCustomerId(purchaseEntity.getCustomer().getId());
                    purchase.setAmount(purchaseEntity.getAmount());
                    purchase.setDateOfPurchase(purchaseEntity.getDateOfPurchase());
                    purchase.setCouponId(purchaseEntity.getCoupon().getId());
                    return purchase;
        }).collect(Collectors.toList()));
        }
        return customer;
    }

}
