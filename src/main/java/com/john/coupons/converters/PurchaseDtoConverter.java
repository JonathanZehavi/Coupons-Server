package com.john.coupons.converters;

import com.john.coupons.dto.Purchase;
import com.john.coupons.dto.PurchaseDetails;
import com.john.coupons.entities.PurchaseEntity;
import com.john.coupons.enums.ErrorType;
import com.john.coupons.exceptions.ApplicationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
