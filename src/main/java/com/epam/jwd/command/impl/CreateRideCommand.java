package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.*;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.factory.impl.LocationFactory;
import com.epam.jwd.factory.impl.PaymentFactory;
import com.epam.jwd.notification.impl.MailSender;
import com.epam.jwd.service.impl.ClientServiceImpl;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class CreateRideCommand implements Command {
    private final ClientServiceImpl clientService = ClientServiceImpl.getInstance();
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.REDIRECT);
        try {
            Ride ride = parseParams(servletRequest);
            clientService.addLocation(ride.getStartLocation());
            clientService.addLocation(ride.getEndLocation());
            long startLocationId = clientService.findLocationByLatitudeLongitude(ride.getStartLocation()).get().getId();
            long endLocationId = clientService.findLocationByLatitudeLongitude(ride.getEndLocation()).get().getId();
            ride.getStartLocation().setId(startLocationId);
            ride.getEndLocation().setId(endLocationId);
            clientService.addRide(ride);
            ride = clientService.findRideByClientIdAndStartLocationId(ride.getClient().getId(), startLocationId).get();
            double price = ride.getDistance()-ride.getClient().getBonusPercent()*ride.getDistance();
            PaymentType paymentType = parsePaymentType(servletRequest);
            Payment payment = PaymentFactory.getInstance().create(ride, paymentType, price, new Random().nextInt(9999999));
            ClientServiceImpl.getInstance().doPay(payment);
            MailSender.MAIL_SENDER.send(ride.getClient().getEmail(), "New Order", "You create new order.\nFrom : "+ride.getStartLocation().getAddress()+"\nTo :"+ride.getEndLocation().getAddress()+"\nWait another message with info about driver!\nThank you for using our service, Your Buber!");

            commandResult.addAttribute("message", "Successful!");
        } catch (Exception e) {
            commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.REDIRECT);
            commandResult.addAttribute("message", "Something wrong on sever, sorry. Try again later");
        }
        return commandResult;
    }

    private PaymentType parsePaymentType(HttpServletRequest servletRequest){
        String currentPaymentType =(String) servletRequest.getSession().getAttribute("currentPaymentType");
        if(currentPaymentType.toUpperCase().equals("CASH")){
            return PaymentType.CASH;
        }
        return PaymentType.CARD;
    }

    private Ride parseParams(HttpServletRequest servletRequest) throws FactoryException {
        String text_from = servletRequest.getParameter("text_from");
        Double from_x = Double.parseDouble(servletRequest.getParameter("from_x"));
        Double from_y = Double.parseDouble(servletRequest.getParameter("from_y"));
        String text_to = servletRequest.getParameter("text_to");
        Double to_x = Double.parseDouble(servletRequest.getParameter("to_x"));
        Double to_y = Double.parseDouble(servletRequest.getParameter("to_y"));
        Location from = LocationFactory.getInstance().create(from_x, from_y,text_from);
        Location to = LocationFactory.getInstance().create(to_x, to_y,text_to);
        String distanceString = servletRequest.getParameter("dist");
        Double distance = parseDistance(distanceString);
        Client client = (Client)servletRequest.getSession().getAttribute("client");
        return Ride.newBuilder().addDistance(distance).addClient(client).addStartLocation(from)
                .addEndLocation(to).addStartDate(LocalDateTime.now()).build();

    }

    private Double parseDistance(String distanceStr){
        Double distance;
        if(distanceStr.contains("км")){
            distance = Double.parseDouble(distanceStr.substring(0,distanceStr.indexOf('к')-1).replaceAll(",","."));
        }else{
            distance=Double.parseDouble(distanceStr.substring(0, distanceStr.indexOf('м')-1).trim().replaceAll(",", "."));
            distance*=0.001;
        }
        return distance;
    }
}
