package com.john.coupons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    private Long id;
    private int amount;
    private LocalDateTime dateOfPurchase;
    private Long couponId;
    private Long customerId;
    private float totalPrice;

}


