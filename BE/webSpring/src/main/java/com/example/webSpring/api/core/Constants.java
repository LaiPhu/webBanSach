package com.example.webSpring.api.core;

public class Constants {

    public static final String SYSTEM = "system";
    public static final String BASE_WEB_URL = "http://localhost:8088";
    public static final String WEB_URL = "http://localhost:4200";

    private Constants() {}

    public static final class SoftDeletion {

        public static final String KEY = "deleted";
        public static final String DELETED_DATE = "deleted_date";
        public static final String DELETED_BY = "deleted_by";
        public static final boolean DEFAULT_OPERATOR = false;
    }



    public static final class Api {

        public static class Tag {

            public static final String HOME = "home";
            public static final String BOOK = "book";
            public static final String ACCOUNT = "account";
            public static final String HOME_MANAGEMENT = "Home Management";
            public static final String BOOK_MANAGEMENT = "Book Management";
            public static final String ACCOUNT_MANAGEMENT = "Account Management";

        }

        public static class FieldExample {

            public static final String ID = "d7fecfdeee394a79b849dac42ae20c9a";
            public static final String  IDS = "[\"d7fecfdeee394a79b849dac42ae20c9a\"]";
            public static final String NAME = "Tuổi trẻ";
            public static final String PRICE = "100000";
            public static final String DESCRIPTION = "Một buổi sáng.....";
            public static final String TRANSLATOR = "Nguyễn Thành Nam";
            public static final String IMAGE = "abc.jpg";
            public static final String EMAIL = "abc123@gmail.com";
            public static final String PHONE_NUMBER = "0312345678";
            public static final String DATE = "2345678";
            public static final String USER_NAME = "laiphu123";
            public static final String PASS_WORD = "abc@123";

        }


        public static class FieldDescription {

        }

        public static class Path {

            public static final String PREFIX = "/api";



            public static class Auth {

                public static final String REFRESH_TOKEN = "/refresh-token";
            }

            public static class Account {

            }

            public static class ComCat {

            }

            public static class PetIdCardRequest {

            }
        }
    }


    public static class ValidationMessage {

        public static final String FIELD_IS_REQUIRED = "validation.mustNotBeNull";

        public static final String INVALID_PASSWORD = "validation.invalidPassword";
        public static final String INVALID_USER_NAME = "validation.invalidUserName";
        public static final String INVALID_FIRST_NAME = "validation.invalidFirstName";
        public static final String INVALID_LAST_NAME = "validation.invalidLastName";

        public static final String INVALID_DISPLAY_NAME = "validation.invalidDisplayName";
        public static final String INVALID_COUNTRY_CODE = "validation.invalidCountryCode";

        public static final String NOT_FOUND_USER = "validation.notFoundUser";

        public static final String INVALID_MIN_VALUE = "validation.invalidMinValue";
        public static final String INVALID_MAX_VALUE = "validation.invalidMaxValue";
        public static final String INVALID_SIZE_VALUE = "validation.invalidSizeValue";
        public static final String INVALID_EMAIL_FORMAT = "validation.invalidEmailFormat";

        public static final String RESET_PW_TOKEN = "validation.resetPWToken";
    }
}
