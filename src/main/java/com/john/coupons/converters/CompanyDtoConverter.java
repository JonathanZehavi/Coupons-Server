package com.john.coupons.converters;

import com.john.coupons.dto.Company;
import com.john.coupons.dto.Coupon;
import com.john.coupons.entities.CompanyEntity;

import java.util.stream.Collectors;

public class CompanyDtoConverter {

    public static Company from(CompanyEntity companyEntity) {
        Company company = new Company();
        company.setId(companyEntity.getId());
        company.setCompanyName(companyEntity.getCompanyName());
        company.setPhoneNumber(companyEntity.getPhoneNumber());
        company.setAddress(companyEntity.getAddress());
        if (company.getCoupons() != null) {
            company.setCoupons(companyEntity.getCoupons()
                    .stream()
                    .map(couponEntity -> {
                        Coupon coupon = new Coupon();
                        coupon.setId(couponEntity.getId());
                        coupon.setTitle(couponEntity.getTitle());
                        coupon.setDescription(couponEntity.getDescription());
                        coupon.setStartDate(couponEntity.getStartDate());
                        coupon.setEndDate(couponEntity.getEndDate());
                        coupon.setAmount(couponEntity.getAmount());
                        coupon.setPrice(couponEntity.getPrice());
                        coupon.setImage(couponEntity.getImage());
                        coupon.setCategory(couponEntity.getCategory());
                        return coupon;
                    }).collect(Collectors.toList()));
        }
        return company;
    }
}
