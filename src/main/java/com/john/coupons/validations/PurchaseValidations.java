package com.john.coupons.validations;

import com.john.coupons.dto.Purchase;
import com.john.coupons.enums.ErrorType;
import com.john.coupons.exceptions.ApplicationException;
import org.springframework.stereotype.Component;

@Component
public class PurchaseValidations {

    public void validatePurchase(Purchase purchase) throws ApplicationException {
        if (purchase.getAmount() <= 0) {
            throw new ApplicationException(ErrorType.INVALID_AMOUNT);
        }
    }
}
