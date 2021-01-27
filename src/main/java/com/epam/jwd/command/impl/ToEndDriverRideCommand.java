package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.dao.impl.DriverDao;
import com.epam.jwd.domain.impl.Car;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.domain.impl.Payment;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.notification.impl.MailSender;
import com.epam.jwd.service.impl.ClientServiceImpl;
import com.epam.jwd.service.impl.DriverServiceImpl;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class ToEndDriverRideCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("WEB-INF/jsp/driver/end_ride.jsp", CommandResult.ResponseType.FORWARD);
        Long carId = Long.parseLong(servletRequest.getParameter("driverCar"));
        Long rideId = Long.parseLong(servletRequest.getParameter("ride_id"));
        Driver driver =(Driver) servletRequest.getSession().getAttribute("driver");
        try {
            Optional<Car> carOptional = DriverServiceImpl.getInstance().findCarById(carId);
            Optional<Ride> ride = DriverServiceImpl.getInstance().findRideById(rideId);
            Optional<Payment> paymentByRideId = DriverServiceImpl.getInstance().findPaymentByRideId(rideId);
            if(carOptional.isEmpty() || ride.isEmpty() || paymentByRideId.isEmpty()){
                throw new ServiceException("Something wrong");
            }
            ride.get().setDriver(driver);
            DriverServiceImpl.getInstance().updateRideDriver(ride.get(), driver);
            Car car = carOptional.get();
            MailSender.MAIL_SENDER.send(ride.get().getClient().getEmail(), "Your order!", "Wait a "+car.getColor()+" "+car.getBrand()+" "+car.getModel()+" with number : "+car.getNumber()+"!");
            commandResult.addAttribute("payment", paymentByRideId.get());
            commandResult.addAttribute("ride", ride.get());
        } catch (ServiceException | IOException | MessagingException e) {
            commandResult = new CommandResult("driver?command=to_available_rides", CommandResult.ResponseType.REDIRECT);
            commandResult.addAttribute("message" , "Something was wrong");
        }
        return commandResult;
    }
}
