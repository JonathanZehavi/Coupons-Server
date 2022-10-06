package com.john.coupons.validations;

import com.john.coupons.dto.Customer;
import com.john.coupons.enums.ErrorType;
import com.john.coupons.exceptions.ApplicationException;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidations {

    public void validateCustomer(Customer customer) throws ApplicationException {
        if (customer.getAddress() == null) {
            throw new ApplicationException(ErrorType.INVALID_ADDRESS);
        }
        if (customer.getBirthday() == null) {
            throw new ApplicationException(ErrorType.INVALID_AGE);
        }
        if (customer.getPhoneNumber() == null) {
            throw new ApplicationException(ErrorType.INVALID_PHONE_NUMBER);
        }
    }
}

