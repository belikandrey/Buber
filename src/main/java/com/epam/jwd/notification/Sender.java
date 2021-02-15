package com.epam.jwd.notification;

import javax.mail.MessagingException;

public abstract class Sender {
    public abstract void send(String to, String title, String messageText) throws MessagingException;
}
