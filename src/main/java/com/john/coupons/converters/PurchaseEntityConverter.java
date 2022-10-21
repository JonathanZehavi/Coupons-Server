package com.john.coupons.converters;

import com.john.coupons.dto.Purchase;
import com.john.coupons.entities.PurchaseEntity;

import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;


public class PurchaseEntityConverter {

    public static PurchaseEntity from(@NotNull Purchase purchase) {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setDateOfPurchase(purchase.getDateOfPurchase());
        purchaseEntity.setAmount(purchase.getAmount());
        purchaseEntity.setTotalPrice(purchase.getTotalPrice());
        purchaseEntity.setCoupons(purchase.getCoupons().stream().map(CouponEntityConverter::from).collect(Collectors.toList()));
        return purchaseEntity;
    }
}
