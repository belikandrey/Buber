package com.epam.jwd.validator.impl;

import com.epam.jwd.validator.Validator;

public class CardDateValidator implements Validator<String> {

    public static final CardDateValidator CARD_DATE_VALIDATOR = new CardDateValidator();

    private final String regex = "(?:0[1-9]|1[0-2])/[0-9]{2}";
    private CardDateValidator(){}

    @Override
    public boolean validate(String s) {
        return s.matches(regex);
    }
}
