package com.john.coupons.converters;

import com.john.coupons.dto.Coupon;
import com.john.coupons.entities.CouponEntity;

public class CouponDtoConverter {

    public static Coupon from(CouponEntity couponEntity) {
        Coupon coupon = new Coupon();
        coupon.setId(couponEntity.getId());
        coupon.setCategory(couponEntity.getCategory());
        coupon.setAmount(couponEntity.getAmount());
        coupon.setDescription(couponEntity.getDescription());
        coupon.setTitle(couponEntity.getTitle());
        coupon.setPrice(couponEntity.getPrice());
        coupon.setStartDate(couponEntity.getStartDate());
        coupon.setEndDate(couponEntity.getEndDate());
        coupon.setImage(couponEntity.getImage());
        coupon.setCompanyId(couponEntity.getCompany().getId());
        return coupon;
    }
}
