package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Payment;
import com.epam.jwd.domain.impl.PaymentType;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.factory.impl.PaymentFactory;
import com.epam.jwd.notification.impl.MailSender;
import com.epam.jwd.service.impl.ClientServiceImpl;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

public class ClientSubmitCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.REDIRECT);
        PaymentType paymentType = parsePaymentType(servletRequest.getParameter("type_payment"));
        Long rideId = Long.parseLong(servletRequest.getParameter("ride_id"));
        try {
            Ride ride = ClientServiceImpl.getInstance().findRideById(rideId).get();
            double price = ride.getDistance()-ride.getClient().getBonusPercent()*ride.getDistance();
            Payment payment = PaymentFactory.getInstance().create(ride, paymentType, price, new Random().nextInt(9999999));
            ClientServiceImpl.getInstance().doPay(payment);
            MailSender.MAIL_SENDER.send(ride.getClient().getEmail(), "New Order", "You create new order.\nFrom : "+ride.getStartLocation().getAddress()+"\nTo :"+ride.getEndLocation().getAddress()+"\nWait another message with info about driver!\nThank you for using our service, Your Buber!");
        } catch (ServiceException | FactoryException | IOException | MessagingException e) {
            commandResult.addAttribute("message", "Something was wrong...");
        }
        return commandResult;
    }
    private PaymentType parsePaymentType(String paymentTypeString){
        if(paymentTypeString.toUpperCase().trim().equals("CASH")){
            return PaymentType.CASH;
        }
        return PaymentType.CARD;
    }
}
