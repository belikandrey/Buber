package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.BankCard;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.Location;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.factory.impl.LocationFactory;
import com.epam.jwd.notification.impl.MailSender;
import com.epam.jwd.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public class ToCreateRideSubmitCommand implements Command {
    private final ClientServiceImpl clientService = ClientServiceImpl.getInstance();
    @Override
    public CommandResult execute(HttpServletRequest servletRequest){
        CommandResult commandResult = new CommandResult("WEB-INF/jsp/client/create_ride_submit.jsp", CommandResult.ResponseType.FORWARD);
         try {
            Ride ride = parseParams(servletRequest);
            clientService.addLocation(ride.getStartLocation());
            clientService.addLocation(ride.getEndLocation());
             long startLocationId = clientService.findLocationByLatitudeLongitude(ride.getStartLocation()).get().getId();
             long endLocationId = clientService.findLocationByLatitudeLongitude(ride.getEndLocation()).get().getId();
             ride.getStartLocation().setId(startLocationId);
             ride.getEndLocation().setId(endLocationId);
             clientService.addRide(ride);
             List<BankCard> allClientCard = ClientServiceImpl.getInstance().findAllClientCard(ride.getClient().getLogin());
             commandResult.addAttribute("cards",allClientCard);
             ride = clientService.findRideByClientIdAndStartLocationId(ride.getClient().getId(), startLocationId).get();
             commandResult.addAttribute("ride", ride);

        } catch (FactoryException | ServiceException e) {
            commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.REDIRECT);
            commandResult.addAttribute("message","Something wrong on sever, sorry. Try again later");
        }
         return commandResult;
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
