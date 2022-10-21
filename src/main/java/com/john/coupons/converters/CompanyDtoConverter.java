package com.john.coupons.converters;

import com.john.coupons.dto.Company;
import com.john.coupons.entities.CompanyEntity;


public class CompanyDtoConverter {

    public static Company from(CompanyEntity companyEntity) {
        Company company = new Company();
        company.setId(companyEntity.getId());
        company.setCompanyName(companyEntity.getCompanyName());
        company.setPhoneNumber(companyEntity.getPhoneNumber());
        company.setAddress(companyEntity.getAddress());

        return company;
    }
}
