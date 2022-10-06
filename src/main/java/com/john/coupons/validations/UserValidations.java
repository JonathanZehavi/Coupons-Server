package com.john.coupons.validations;

import com.john.coupons.dto.User;
import com.john.coupons.enums.ErrorType;
import com.john.coupons.enums.Role;
import com.john.coupons.exceptions.ApplicationException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidations {

    public void validateUser(User user) throws ApplicationException {
        if (!emailValidation(user.getUsername())) {
            throw new ApplicationException(ErrorType.INVALID_EMAIL);
        }
        if (!passwordValidation(user.getPassword())) {
            throw new ApplicationException(ErrorType.INVALID_PASSWORD);
        }
        if (user.getRole() == Role.Company && user.getCompanyId() == null) {
            throw new ApplicationException(ErrorType.MUST_CONTAIN_COMPANY_ID);

        }
        if (user.getRole() != Role.Company && user.getCompanyId() != null) {
            throw new ApplicationException(ErrorType.CANNOT_CONTAIN_COMPANY_ID);
        }
    }

    private boolean emailValidation(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean passwordValidation(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

