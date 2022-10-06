package com.john.coupons.exceptions;

import com.john.coupons.enums.ErrorType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public ErrorResponse toResponse(Throwable throwable, HttpServletResponse response) {

        if (throwable instanceof ApplicationException) {
            ApplicationException applicationException = (ApplicationException) throwable;

            ErrorType errorType = applicationException.getErrorType();
            int errorNumber = errorType.getErrorNumber();
            String errorMessage = errorType.getErrorMessage();
            response.setStatus(errorNumber);

            ErrorResponse errorResponse = new ErrorResponse(errorNumber, errorMessage);
            if (applicationException.getErrorType().isShowStackTrace()) {
                applicationException.printStackTrace();
            }
            return errorResponse;
        }

        response.setStatus(ErrorType.GENERAL_ERROR.getErrorNumber());
        ErrorResponse errorResponse = new ErrorResponse(ErrorType.GENERAL_ERROR.getErrorNumber(), ErrorType.GENERAL_ERROR.getErrorMessage());
        throwable.printStackTrace();
        return errorResponse;
    }
}
