package com.epam.jwd.validator.impl;

import com.epam.jwd.validator.Validator;

public class EmailValidator implements Validator<String> {

    public static final EmailValidator EMAIL_VALIDATOR = new EmailValidator();

    private EmailValidator() {
    }

    private org.apache.commons.validator.routines.EmailValidator commonEmailValidator = org.apache.commons.validator.routines.EmailValidator.getInstance();

    @Override
    public boolean validate(String s) {
        return commonEmailValidator.isValid(s);
    }
}
