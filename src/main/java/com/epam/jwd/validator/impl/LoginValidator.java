package com.epam.jwd.validator.impl;

import com.epam.jwd.validator.Validator;

public class LoginValidator implements Validator<String> {

    public static final LoginValidator LOGIN_VALIDATOR = new LoginValidator();

    private LoginValidator(){}

    private final String regex = "^[A-Za-z]\\w{5,29}$";

    @Override
    public boolean validate(String s) {
        return s.matches(regex);
    }
}
