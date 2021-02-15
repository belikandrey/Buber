package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.Rating;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ClientService;
import com.epam.jwd.service.DriverService;
import com.epam.jwd.service.impl.ClientServiceImpl;
import com.epam.jwd.service.impl.DriverServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

public class EndRideCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(EndRideCommand.class);
    private final DriverService driverService = DriverServiceImpl.getInstance();
    private final ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.REDIRECT);
        long rideId = Long.parseLong(servletRequest.getParameter("ride_id"));
        int clientMark = Integer.parseInt(servletRequest.getParameter("clientMark"));
        try {
            Optional<Ride> rideOptional = driverService.findRideById(rideId);
            if(rideOptional.isEmpty()){
                logger.error("Ride for ending not found");
                throw new ServiceException("Ride not found");
            }
            Ride ride = rideOptional.get();
            Rating rideClientRating = Rating.values()[clientMark - 1];
            ride.setClientMark(rideClientRating);
            Client client = ride.getClient();
            Rating clientRating = client.getRating();
            Rating newRating = newClientRating(clientRating, rideClientRating);
            ride.getClient().setRating(newRating);
            clientService.updateRating(client.getLogin(), newRating.ordinal()+1);
            driverService.updateRideEndDate(rideId, LocalDateTime.now());
        } catch (ServiceException e) {
            logger.error("Ride not ended : "+e.getMessage());
            commandResult.addAttribute("message", "Something was wrong");
        }
        return commandResult;
    }
    private Rating newClientRating(Rating clientRating, Rating rideClientRating){
        int clientOrdinal = clientRating.ordinal()+1;
        int rideClientOrdinal = rideClientRating.ordinal();
        if(clientOrdinal<rideClientOrdinal){
            clientOrdinal++;
        }else if(clientOrdinal>rideClientOrdinal){
            clientOrdinal--;
        }
        return Rating.values()[clientOrdinal];
    }
}
