package com.intellimedia.practical.utils;

import java.util.regex.Pattern;

public class Validator {

    final static Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+");

    static final Pattern FULLNAME_PATTERN = Pattern.compile(
            "^[\\p{L} .'-]+$"
    );

    public static boolean isEmailValid(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isFullNameValid(String fullName) {
        return FULLNAME_PATTERN.matcher(fullName).matches();
    }

    public static boolean isFieldBlank(String field) {
        return field.trim().isEmpty();
    }

    public static boolean containsSpace(String field) {
        return field.contains(" ");
    }

    public static boolean isPasswordMatching(String password1, String password2) {
        return password1.equals(password2);
    }
}
