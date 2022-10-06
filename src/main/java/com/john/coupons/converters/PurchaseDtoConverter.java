package com.john.coupons.converters;

import com.john.coupons.dto.Purchase;
import com.john.coupons.entities.PurchaseEntity;

public class PurchaseDtoConverter {

    public static Purchase from(PurchaseEntity purchaseEntity) {
        Purchase purchase = new Purchase();
        purchase.setId(purchaseEntity.getId());
        purchase.setAmount(purchaseEntity.getAmount());
        purchase.setDateOfPurchase(purchaseEntity.getDateOfPurchase());
        purchase.setCouponId(purchaseEntity.getCoupon().getId());
        purchase.setCustomerId(purchaseEntity.getCustomer().getId());
        purchase.setTotalPrice(purchaseEntity.getTotalPrice());
        return purchase;
    }

}
