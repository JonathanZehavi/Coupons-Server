package com.john.coupons.converters;

import com.john.coupons.dto.Company;
import com.john.coupons.entities.CompanyEntity;


public class CompanyEntityConverter {

    public static CompanyEntity from(Company company) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(company.getId());
        companyEntity.setCompanyName(company.getCompanyName());
        companyEntity.setPhoneNumber(company.getPhoneNumber());
        companyEntity.setAddress(company.getAddress());

        return companyEntity;
    }
}
