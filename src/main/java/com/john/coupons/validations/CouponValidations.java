package com.john.coupons.validations;
import com.john.coupons.dto.Coupon;
import com.john.coupons.enums.ErrorType;
import com.john.coupons.exceptions.ApplicationException;
import org.springframework.stereotype.Component;

@Component
public class CouponValidations  {


    public void validateCoupon(Coupon coupon) throws ApplicationException{

        if (coupon.getStartDate() == null || coupon.getEndDate() == null) {
            throw new ApplicationException(ErrorType.INVALID_DATES);
        }
        if (coupon.getAmount() == 0) {
            throw new ApplicationException(ErrorType.INVALID_AMOUNT);
        }
        if (coupon.getPrice() == 0) {
            throw new ApplicationException(ErrorType.INVALID_PRICE);
        }
        if (coupon.getCategory() == null) {
            throw new ApplicationException(ErrorType.INVALID_COUPON_TYPE);
        }

    }
}

