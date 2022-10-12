package com.john.coupons.converters;

import com.john.coupons.dto.Company;
import com.john.coupons.entities.CompanyEntity;
import com.john.coupons.entities.CouponEntity;

import java.util.stream.Collectors;

public class CompanyEntityConverter {

    public static CompanyEntity from(Company company) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(company.getId());
        companyEntity.setCompanyName(company.getCompanyName());
        companyEntity.setPhoneNumber(company.getPhoneNumber());
        companyEntity.setAddress(company.getAddress());
        if (companyEntity.getCoupons() != null) {
            companyEntity.setCoupons(company.getCoupons()
                    .stream()
                    .map(coupon -> {
                        CouponEntity couponEntity = new CouponEntity();
                        couponEntity.setId(coupon.getId());
                        couponEntity.setStartDate(coupon.getStartDate());
                        couponEntity.setEndDate(coupon.getEndDate());
                        couponEntity.setAmount(coupon.getAmount());
                        couponEntity.setTitle(coupon.getTitle());
                        couponEntity.setDescription(coupon.getDescription());
                        couponEntity.setImage(coupon.getImage());
                        couponEntity.setCategory(coupon.getCategory());
                        couponEntity.setPrice(coupon.getPrice());
                        return couponEntity;
                    }).collect(Collectors.toList()));
        }
        return companyEntity;
    }
}
