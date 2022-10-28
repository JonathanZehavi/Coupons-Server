package com.john.coupons.validations;

import com.john.coupons.dto.Company;
import com.john.coupons.enums.ErrorType;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.service.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyValidations {

    public final CompaniesService companiesService;

    @Autowired
    public CompanyValidations(CompaniesService companiesService) {
        this.companiesService = companiesService;
    }

    public void validateCompany(Company company) throws ApplicationException {

        if (company.getCompanyName() == null) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR);
        }
        if (company.getAddress() == null) {
            throw new ApplicationException(ErrorType.INVALID_ADDRESS);
        }
        if (company.getPhoneNumber() == null) {
            throw new ApplicationException(ErrorType.INVALID_PHONE_NUMBER);
        }
    }
}

