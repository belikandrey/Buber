package com.epam.jwd.validator.impl;

import com.epam.jwd.validator.Validator;


public class CardNumberValidator implements Validator<String> {

    public static final CardNumberValidator CARD_NUMBER_VALIDATOR = new CardNumberValidator();

    private CardNumberValidator(){}

    private final String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
            "(?<mastercard>5[1-5][0-9]{14})|" +
            "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
            "(?<amex>3[47][0-9]{13})|" +
            "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
            "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";

    @Override
    public boolean validate(String s) {
        return s.matches(regex);
    }
}
