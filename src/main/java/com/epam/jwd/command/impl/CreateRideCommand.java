package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.*;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.AbstractFactory;
import com.epam.jwd.factory.impl.LocationFactory;
import com.epam.jwd.factory.impl.PaymentFactory;
import com.epam.jwd.notification.Sender;
import com.epam.jwd.notification.impl.MailSender;
import com.epam.jwd.service.ClientService;
import com.epam.jwd.service.impl.ClientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Random;

public class CreateRideCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(CreateRideCommand.class);
    private final ClientService clientService = ClientServiceImpl.getInstance();
    private final AbstractFactory<Location> locationFactory = LocationFactory.getInstance();
    private final AbstractFactory<Payment> paymentFactory = PaymentFactory.getInstance();
    private final Sender sender = MailSender.MAIL_SENDER;

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
            double price = clientService.calculatePrice(ride.getDistance(), ride.getClient().getBonusPercent());
            PaymentType paymentType = parsePaymentType(servletRequest);
            Payment payment = paymentFactory.create(ride, paymentType, price, new Random().nextInt(9999999));
            clientService.doPay(payment);
            sender.send(ride.getClient().getEmail(), "New Order", "You create new order.\nFrom : " +
                    "" + ride.getStartLocation().getAddress() + "\nTo :" + ride.getEndLocation().getAddress() + "" +
                    "\nWait another message with info about driver!\nThank you for using our service, Your Buber!");
            commandResult.addAttribute("message", "Successful!");
            logger.info("Ride created successful");
        } catch (Exception e) {
            logger.error("Ride not created : "+e.getMessage());
            commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("message", "Something wrong on sever, sorry. Try again later");
        }
        return commandResult;
    }

    private PaymentType parsePaymentType(HttpServletRequest servletRequest) {
        String currentPaymentType = (String) servletRequest.getSession().getAttribute("currentPaymentType");
        if (currentPaymentType.equalsIgnoreCase("CASH")) {
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
        Location from = locationFactory.create(from_x, from_y, text_from);
        Location to = locationFactory.create(to_x, to_y, text_to);
        String distanceString = servletRequest.getParameter("dist");
        Double distance = parseDistance(distanceString);
        Client client = (Client) servletRequest.getSession().getAttribute("client");

        return Ride.newBuilder().addDistance(distance).addClient(client).addStartLocation(from)
                .addEndLocation(to).addStartDate(LocalDateTime.now()).build();

    }

    private Double parseDistance(String distanceStr) {
        Double distance;
        if (distanceStr.contains("км")) {
            distance = Double.parseDouble(distanceStr.substring(0, distanceStr.indexOf('к') - 1).replaceAll(",", "."));
        } else {
            distance = Double.parseDouble(distanceStr.substring(0, distanceStr.indexOf('м') - 1).trim().replaceAll(",", "."));
            distance *= 0.001;
        }
        return distance;
    }
}
