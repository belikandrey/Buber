package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.Rating;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.ClientServiceImpl;
import com.epam.jwd.service.impl.DriverServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

public class EndRideCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.REDIRECT);
        Long rideId = Long.parseLong(servletRequest.getParameter("ride_id"));
        Integer clientMark = Integer.parseInt(servletRequest.getParameter("clientMark"));
        try {
            Optional<Ride> rideOptional = DriverServiceImpl.getInstance().findRideById(rideId);
            if(rideOptional.isEmpty()){
                throw new ServiceException("Ride not found");
            }
            Ride ride = rideOptional.get();
            Rating rideClientRating = Rating.values()[clientMark - 1];
            ride.setClientMark(rideClientRating);
            Client client = ride.getClient();
            Rating clientRating = client.getRating();
            Rating newRating = newClientRating(clientRating, rideClientRating);
            ride.getClient().setRating(newRating);
            ClientServiceImpl.getInstance().updateRating(client.getLogin(), newRating.ordinal()+1);
            DriverServiceImpl.getInstance().updateRideEndDate(rideId, LocalDateTime.now());
            System.out.println("GDFSMKFGDNSKFSD<MF<DS");
        } catch (ServiceException e) {
            e.printStackTrace();
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
