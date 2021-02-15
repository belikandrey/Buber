package com.epam.jwd.notification.impl;

import com.epam.jwd.context.ApplicationProperties;
import com.epam.jwd.notification.Sender;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.Locale;


public class SmsSender extends Sender {

    public static final SmsSender SMS_SENDER = new SmsSender();


    private SmsSender() {
        Twilio.init(ApplicationProperties.APPLICATION_PROPERTIES.getSmsSid(), ApplicationProperties.APPLICATION_PROPERTIES.getSmsToken());
    }

    private final String TWILIO_NUMBER = ApplicationProperties.APPLICATION_PROPERTIES.getNumber();

    @Override
    public void send(String to, String title, String messageText) {
        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(TWILIO_NUMBER),
                title.toUpperCase(Locale.ROOT) + "\n" + messageText
        ).create();
    }
}
