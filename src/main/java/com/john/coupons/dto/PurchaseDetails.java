package com.john.coupons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetails {

    private String firstname;
    private String lastname;
    private String username;
    private String address;
    private String phoneNumber;
    private int amount;
    private LocalDateTime dateOfPurchase;
    private String title;
    private String description;
    private float price;
    private String companyName;

}
