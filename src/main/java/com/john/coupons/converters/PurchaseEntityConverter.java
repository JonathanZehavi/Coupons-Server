package com.john.coupons.converters;

import com.john.coupons.dto.Purchase;
import com.john.coupons.entities.PurchaseEntity;
import lombok.Data;

import org.jetbrains.annotations.NotNull;


@Data
public class PurchaseEntityConverter {

    public static PurchaseEntity from(@NotNull Purchase purchase) {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setDateOfPurchase(purchase.getDateOfPurchase());
        purchaseEntity.setAmount(purchase.getAmount());
        purchaseEntity.setTotalPrice(purchase.getTotalPrice());

        return purchaseEntity;
    }
}
