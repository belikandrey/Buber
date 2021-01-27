package com.epam.jwd.notification.impl;

import com.epam.jwd.exception.InputFileNotFound;
import com.epam.jwd.notification.Sender;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class MailSender extends Sender {

    public static final MailSender MAIL_SENDER = new MailSender();


    //private final String filePath;
    private final Properties properties;
    private final String user;
    private final String password;


    private MailSender() {
        properties = new Properties();
        try {
            URL resource = getClass().getClassLoader().getResource("mail.properties");
            properties.load(new FileReader(new File(resource.toURI())));
        } catch (IOException | URISyntaxException e) {
            throw new InputFileNotFound("File not found!");
        }
        user = properties.getProperty("mail.smtps.user");
        password = properties.getProperty("mail.smtps.password");
    }


    @Override
    public void send(String to,String title, String messageText) throws IOException, MessagingException {

        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);

        message.setFrom(new InternetAddress(user));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(title);
        message.setText(messageText);

        Transport tr = mailSession.getTransport();
        tr.connect(user, password);
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
    }
}
