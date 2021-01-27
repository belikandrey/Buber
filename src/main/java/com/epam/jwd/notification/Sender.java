package com.epam.jwd.notification;

import javax.mail.MessagingException;
import java.io.IOException;

public abstract class Sender {
    public abstract void send(String to, String title,  String messageText) throws IOException, MessagingException;
}
