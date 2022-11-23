package com.john.coupons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetails {

    private Long id;
    private Long userId;
    private String firstname;
    private String lastname;
    private String username;
    private String address;
    private String phoneNumber;
    private float priceInTotal;
    private int amount;
    private LocalDateTime dateOfPurchase;
    private List<Coupon> coupons;

}
