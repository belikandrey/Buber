package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Car;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.domain.impl.Payment;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.notification.Sender;
import com.epam.jwd.notification.impl.MailSender;
import com.epam.jwd.service.DriverService;
import com.epam.jwd.service.impl.DriverServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ToEndRideCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(ToEndRideCommand.class);
    private final DriverService driverService = DriverServiceImpl.getInstance();
    private final Sender sender = MailSender.MAIL_SENDER;

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {

        CommandResult commandResult = new CommandResult("WEB-INF/jsp/driver/end_ride.jsp", CommandResult.ResponseType.FORWARD);
        Car car = (Car) servletRequest.getSession().getAttribute("currentCar");
        Long rideId = Long.parseLong(servletRequest.getParameter("ride_id"));
        Driver driver = (Driver) servletRequest.getSession().getAttribute("driver");
        try {
            Optional<Ride> ride = driverService.findRideById(rideId);
            Optional<Payment> paymentByRideId = driverService.findPaymentByRideId(rideId);
            if (ride.isEmpty() || paymentByRideId.isEmpty()) {
                logger.error("Ride or Payment not found in End Ride");
                throw new ServiceException("Something wrong. Try again later");
            }
            ride.get().setDriver(driver);
            driverService.updateRideDriver(ride.get(), driver);
            sender.send(ride.get().getClient().getEmail(), "Your order!", "Wait a " +
                    "" + car.getColor() + " " + car.getBrand() + " " + car.getModel() + " with number : " +
                    "" + car.getNumber() + "!\nYou can contact the driver by this number : " +
                    "" + car.getDriver().getPhoneNumber() + "!\nYour Buber.");
            commandResult.addAttribute("payment", paymentByRideId.get());
            commandResult.addAttribute("ride", ride.get());
        } catch (ServiceException | MessagingException e) {
            logger.error("Exception in end ride : " + e.getMessage());
            commandResult = new CommandResult("driver?command=to_available_rides", CommandResult.ResponseType.REDIRECT);
            commandResult.addAttribute("message", "Something was wrong");
        }
        return commandResult;
    }
}
