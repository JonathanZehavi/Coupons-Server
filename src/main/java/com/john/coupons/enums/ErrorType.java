package com.john.coupons.enums;

    public enum ErrorType {

        GENERAL_ERROR(601, "General Error", true),
        USER_DOES_NOT_EXIST(602, "The user does not exist", false),
        LOGIN_FAILED(603, "Login failed. Please try again.", false),
        NAME_ALREADY_EXISTS(604, "The name you chose already exist. Please enter another name", false),
        MUST_ENTER_NAME(605, "Cannot insert a null or empty name", false),
        MUST_CONTAIN_COMPANY_ID(606,"User of this type must contain company id", false),
        ID_DOES_NOT_EXIST(607, "This ID does not exist", false),
        INVALID_PASSWORD(608, "Password must contains minimum eight characters, at least one letter and one number", false),
        NOT_ENOUGH_COUPONS_LEFT(609, "Not enough coupons left to purchase the amount you've requested", false),
        COUPON_EXPIRED(610, "The coupon is expired", false),
        COUPON_TITLE_EXIST(611, "The title of this coupon is already exist, please change the title", false),
        INVALID_PRICE(612, "Coupons price must be more than 0", false),
        INVALID_EMAIL(613, "Email address is invalid, Please enter a valid email address", false),
        INVALID_AMOUNT(614, "Coupons amount must be more than 0", false),
        INVALID_DATES(615, "The dates you've entered are wrong or null", false),
        MUST_INSERT_A_VALUE(616, "Must insert a value", false),
        COULD_NOT_GENERATE_ID(617, "could not generate Id", false),
        NAME_TOO_SHORT(618, "The name must contain at least two letters", false),
        INVALID_COUPON_TYPE(619, "Can not insert a null/empty coupon type", false),
        INVALID_ID(620, "Id cannot be empty or null and must contain at least one digit", false),
        INVALID_ADDRESS(621, "Address is invalid, Please enter a valid address", false),
        INVALID_PHONE_NUMBER(622, "Phone number is invalid, Please enter a valid phone number", false),
        NO_PERMISSION_GRANTED(623, "No permission granted", true),
        CANNOT_CONTAIN_COMPANY_ID(624, "User of this type cannot contain company id", false),
        PASSWORD_DOES_NOT_MATCH(625, "Incorrect password", false),
        COULD_NOT_FIND_COMPANY_NAME(626, "Could not find the name of the company, try again", false),
        COMPANY_DOES_NOT_EXIST(627, "The company does not exist", false),
        COUPON_DOES_NOT_EXIST(628,"The coupon you are looking for does not exist", false),
        CUSTOMER_DOES_NOT_EXIST(629,"The customer that you are looking for does not exist" ,false),
        INVALID_CATEGORY(630,"You must enter an offered category", false),
        INVALID_AGE(631,"You must enter age" ,false),
        INVALID_NAME(632,"You must enter a name" ,false),
        COUPON_START_DATE_MUST_BE_IN_THE_FUTURE(633, "Coupon's start date must be later than today"),
        CATEGORY_ALREADY_EXIST(634, "Category already exist"),
        EMPTY_COUPONS_LIST(635, "Couldn't find any coupons"),
        EMPTY_COMPANY_LIST(636, "Couldn't find any companies"),
        START_DATE_MUST_BE_BEFORE_END_DATE(637, "Coupons 'Start Date' must be before 'End Date'"),
        EMPTY_LIST(638,"The list you are trying to get is empty");



        private int errorNumber;
        private String errorName;
        private String errorMessage;
        private boolean isShowStackTrace;

        private ErrorType(int errorNumber, String internalMessage, boolean isShowStackTrace) {
            this.errorNumber = errorNumber;
            this.errorMessage = internalMessage;
            this.isShowStackTrace = isShowStackTrace;
        }

        public String getErrorName() {
            return errorName;
        }

        public void setErrorName(String errorName) {
            this.errorName = errorName;
        }

        private ErrorType(int errorNumber, String internalMessage) {
            this.errorNumber = errorNumber;
            this.errorMessage = internalMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public boolean isShowStackTrace() {
            return isShowStackTrace;
        }

        public int getErrorNumber() {
            return errorNumber;
        }
    }



