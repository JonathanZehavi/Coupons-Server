package com.john.coupons.converters;

import com.john.coupons.dto.PurchaseDetails;
import com.john.coupons.entities.PurchaseEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

public class PurchaseDetailsConverter {

    public static PurchaseDetails from(PurchaseEntity purchaseEntity) {
        PurchaseDetails purchaseDetails = new PurchaseDetails();
        purchaseDetails.setId(purchaseEntity.getId());
        purchaseDetails.setUserId(purchaseEntity.getCustomer().getId());
        purchaseDetails.setFirstname(purchaseEntity.getCustomer().getUser().getFirstname());
        purchaseDetails.setLastname(purchaseEntity.getCustomer().getUser().getLastname());
        purchaseDetails.setUsername(purchaseEntity.getCustomer().getUser().getUsername());
        purchaseDetails.setAddress(purchaseEntity.getCustomer().getAddress());
        purchaseDetails.setPhoneNumber(purchaseEntity.getCustomer().getPhoneNumber());
        purchaseDetails.setAmount(purchaseEntity.getAmount());
        purchaseDetails.setDateOfPurchase(LocalDateTime.of(
                purchaseEntity.getDateOfPurchase().getYear(),
                purchaseEntity.getDateOfPurchase().getMonth(),
                purchaseEntity.getDateOfPurchase().getDayOfMonth(),
                purchaseEntity.getDateOfPurchase().getHour(),
                purchaseEntity.getDateOfPurchase().getMinute(),
                purchaseEntity.getDateOfPurchase().getSecond()
        ));
        purchaseDetails.setCoupons(purchaseEntity.getCoupons()
                .stream()
                .map(CouponDtoConverter::from).collect(Collectors.toList()));
        return purchaseDetails;
    }
}
