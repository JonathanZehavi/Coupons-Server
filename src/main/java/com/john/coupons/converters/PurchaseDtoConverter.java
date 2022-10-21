package com.john.coupons.converters;

import com.john.coupons.dto.Purchase;
import com.john.coupons.entities.PurchaseEntity;

import java.util.stream.Collectors;

public class PurchaseDtoConverter {

    public static Purchase from(PurchaseEntity purchaseEntity) {
        Purchase purchase = new Purchase();
        purchase.setId(purchaseEntity.getId());
        purchase.setAmount(purchaseEntity.getAmount());
        purchase.setDateOfPurchase(purchaseEntity.getDateOfPurchase());
        purchase.setCustomerId(purchaseEntity.getCustomer().getId());
        purchase.setTotalPrice(purchaseEntity.getTotalPrice());
        purchase.setCoupons(purchaseEntity.getCoupons()
                .stream()
                .map(CouponDtoConverter::from).collect(Collectors.toList()));
        return purchase;
    }

}
