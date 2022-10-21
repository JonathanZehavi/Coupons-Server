package com.john.coupons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Long id;
    private User user;
    private String address;
    private int amountOfChildren;
    private LocalDate birthday;
    private String phoneNumber;

}