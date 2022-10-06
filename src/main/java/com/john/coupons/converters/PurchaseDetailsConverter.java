package com.john.coupons.converters;

import com.john.coupons.dto.PurchaseDetails;
import com.john.coupons.entities.PurchaseEntity;

public class PurchaseDetailsConverter {

    public static PurchaseDetails from(PurchaseEntity purchaseEntity){

        PurchaseDetails purchaseDetails = new PurchaseDetails();
        purchaseDetails.setFirstname(purchaseEntity.getCustomer().getUser().getFirstname());
        purchaseDetails.setLastname(purchaseEntity.getCustomer().getUser().getLastname());
        purchaseDetails.setUsername(purchaseEntity.getCustomer().getUser().getLastname());
        purchaseDetails.setAddress(purchaseEntity.getCustomer().getAddress());
        purchaseDetails.setPhoneNumber(purchaseEntity.getCustomer().getPhoneNumber());
        purchaseDetails.setAmount(purchaseEntity.getAmount());
        purchaseDetails.setDateOfPurchase(purchaseEntity.getDateOfPurchase());
        purchaseDetails.setTitle(purchaseEntity.getCoupon().getTitle());
        purchaseDetails.setDescription(purchaseEntity.getCoupon().getDescription());
        purchaseDetails.setPrice(purchaseEntity.getCoupon().getPrice());
        purchaseDetails.setCompanyName(purchaseEntity.getCoupon().getCompany().getCompanyName());

        return purchaseDetails;
    }
}
