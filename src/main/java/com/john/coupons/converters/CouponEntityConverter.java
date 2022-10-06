package com.john.coupons.converters;

import com.john.coupons.dto.Coupon;
import com.john.coupons.entities.CouponEntity;
import com.john.coupons.service.CompaniesService;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class CouponEntityConverter {

    @Autowired
    public static CompaniesService companiesService;

    @SneakyThrows
    public static CouponEntity from(Coupon coupon) {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setId(coupon.getId());
        couponEntity.setCategory(coupon.getCategory());
        couponEntity.setAmount(coupon.getAmount());
        couponEntity.setDescription(coupon.getDescription());
        couponEntity.setTitle(coupon.getTitle());
        couponEntity.setPrice(coupon.getPrice());
        couponEntity.setStartDate(coupon.getStartDate());
        couponEntity.setEndDate(coupon.getEndDate());
        couponEntity.setImage(coupon.getImage());
        return couponEntity;
    }
}
