package com.john.coupons.exceptions;

import com.john.coupons.enums.ErrorType;
import lombok.Data;

@Data
public class ApplicationException extends Exception {

    private ErrorType errorType;

    public ApplicationException(ErrorType errorType) {
        this.errorType = errorType;
    }


}



