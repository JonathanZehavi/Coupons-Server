package com.john.coupons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private Long id;
    private String companyName;
    private String phoneNumber;
    private String address;
    List<Coupon> coupons;


}

