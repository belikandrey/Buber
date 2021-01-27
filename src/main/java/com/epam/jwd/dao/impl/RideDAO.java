package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.AbstractDao;
import com.epam.jwd.db.ConnectionPool;
import com.epam.jwd.domain.impl.*;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.ServiceException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RideDAO implements AbstractDao<Long, Ride> {
    public static final RideDAO RIDE_DAO = new RideDAO();

    private RideDAO(){}

    private final ConnectionPool connectionPool = ConnectionPool.CONNECTION_POOL;

    private final String SQL_FIND_ALL_RIDES = "select id, client_id, driver_id, start_location_id," +
            " end_location_id, start_date," +
            " end_date, client_mark, driver_mark, distance from ride";
    private final String SQL_FIND_RIDE_BY_ID = SQL_FIND_ALL_RIDES + " where id = ?";
    private final String SQL_REMOVE_RIDE_BY_ID = "delete from ride where id = ?";
    private final String SQL_ADD_RIDE = "insert into ride (client_id, driver_id, start_location_id, end_location_id, start_date, end_date, client_mark, driver_mark, distance)" +
            "values (?,?,?,?,?,?,?,?, ?);";
    private final String SQL_ADD_MIN_RIDE = "insert into ride(client_id, start_location_id, end_location_id, start_date, distance) values(?, ?, ?, ?, ?)";
    private final String SQL_FIND_RIDE_BY_CLIENT_ID = SQL_FIND_ALL_RIDES + " where client_id = ?";
    private final String SQL_FIND_RIDE_BY_DRIVER_ID = SQL_FIND_ALL_RIDES + " where driver_id = ?";
    private final String SQL_UPDATE_RIDE = "update ride set client_id=?, driver_id = ?, start_location_id = ?, end_location_id=?, start_date=?, end_date = ?, client_mark = ?, driver_mark = ?, distance=?  where id=?";
    private final String SQL_UPDATE_END_DATE = "update ride set end_date = ? where id = ?";
    private final String SQL_SET_DRIVER_ID = "update ride set driver_id = ? where id = ?";
    private final String SQL_FIND_RIDE_BY_CLIENT_ID_START_LOCATION_ID = SQL_FIND_ALL_RIDES+" where client_id = ? and start_location_id = ?";
    private final String SQL_UPDATE_DRIVER_MARK = "update ride set driver_mark=? where id = ?";

    @Override
    public List<Ride> findAll() throws DaoException {
        List<Ride> rides = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_RIDES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rides.add(createEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return rides;
    }


    public void updateDriverMark(Long rideId, int newMark) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_DRIVER_MARK)){
            preparedStatement.setInt(1, newMark);
            preparedStatement.setLong(2, rideId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void updateEndDate(Long rideId, LocalDateTime endTime) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_END_DATE)){
            Date date = Date.valueOf(endTime.toLocalDate());
            preparedStatement.setDate(1, date);
            preparedStatement.setLong(2, rideId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Ride> findRideByClientIdAndStartLocationId(Long client_id, Long location_id) throws DaoException {
        Ride ride = null;
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_RIDE_BY_CLIENT_ID_START_LOCATION_ID)){
            preparedStatement.setLong(1, client_id);
            preparedStatement.setLong(2, location_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                ride = createEntity(resultSet);
            }
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(ride);
    }

    @Override
    public Optional<Ride> findEntityById(Long id) throws DaoException {
        Ride ride = null;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_RIDE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                ride = createEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(ride);
    }

    public List<Ride> findRidesByClientId(Long id) throws DaoException {
        return getRides(id, SQL_FIND_RIDE_BY_CLIENT_ID);
    }

    public List<Ride> findRidesByDriverId(Long id) throws DaoException {
        return getRides(id, SQL_FIND_RIDE_BY_DRIVER_ID);
    }

    private List<Ride> getRides(Long id, String find_ride) throws DaoException {
        List<Ride> rides = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(find_ride)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rides.add(createEntity(resultSet));
            }
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        }
        return rides;
    }

    public boolean setDriver(Driver driver, Ride ride) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_DRIVER_ID)){
            preparedStatement.setLong(1, driver.getId());
            preparedStatement.setLong(2, ride.getId());
            return (preparedStatement.executeUpdate()>0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public boolean update(Ride entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_RIDE)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.setLong(10, entity.getId());
            return (preparedStatement.executeUpdate()>0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    private void fillPreparedStatement(Ride entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, entity.getClient().getId());
        preparedStatement.setLong(2, entity.getDriver().getId());
        preparedStatement.setLong(3, entity.getStartLocation().getId());
        preparedStatement.setLong(4, entity.getEndLocation().getId());
        preparedStatement.setDate(5, Date.valueOf(entity.getStartDate().toLocalDate()));
        preparedStatement.setDate(6, Date.valueOf(entity.getEndDate().toLocalDate()));
        preparedStatement.setInt(7, entity.getClientMark().ordinal() + 1);
        preparedStatement.setInt(8, entity.getDriverMark().ordinal() + 1);
        preparedStatement.setDouble(9, entity.getDistance());
    }

    public boolean addMinRide(Ride ride) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_MIN_RIDE)){
            preparedStatement.setLong(1, ride.getClient().getId());
            preparedStatement.setLong(2, LocationDao.LOCATION_DAO.findEntityByCoords(ride.getStartLocation().getLatitude(), ride.getStartLocation().getLongitude()).get().getId());
            preparedStatement.setLong(3, LocationDao.LOCATION_DAO.findEntityByCoords(ride.getEndLocation().getLatitude(), ride.getEndLocation().getLongitude()).get().getId());
            preparedStatement.setDate(4, Date.valueOf(ride.getStartDate().toLocalDate()));
            preparedStatement.setDouble(5, ride.getDistance());
            System.out.println("Client ID : "+ride.getClient().getId());
            System.out.println( LocationDao.LOCATION_DAO.findEntityByCoords(ride.getStartLocation().getLatitude(),ride.getStartLocation().getLongitude()).get().getId());
            System.out.println(LocationDao.LOCATION_DAO.findEntityByCoords(ride.getEndLocation().getLatitude(), ride.getEndLocation().getLongitude()).get().getId());
            System.out.println(Date.valueOf(ride.getStartDate().toLocalDate()));
            System.out.println( ride.getDistance());
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
            return i>0;
        }catch (SQLException | DaoException e){
            System.out.println(e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean remove(Ride entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE_RIDE_BY_ID)) {
            preparedStatement.setLong(1, entity.getId());
            return (preparedStatement.executeUpdate()>0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean add(Ride entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_RIDE)) {
            fillPreparedStatement(entity, preparedStatement);
            return (preparedStatement.executeUpdate()>0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Ride createEntity(ResultSet set) throws DaoException {
        Ride ride = null;
        try {
            long ride_id = set.getLong("id");
            Optional<Client> rideClientOpt = ClientDao.CLIENT_DAO.findEntityById(set.getLong("client_id"));
            Optional<Driver> rideDriverOpt = DriverDao.DRIVER_DAO.findEntityById(set.getLong("driver_id"));
            Optional<Location> rideStartLocationOpt = LocationDao.LOCATION_DAO.findEntityById(set.getLong("start_location_id"));
            Optional<Location> rideEndLocationOpt = LocationDao.LOCATION_DAO.findEntityById(set.getLong("end_location_id"));
            if(rideClientOpt.isEmpty() || rideStartLocationOpt.isEmpty() || rideEndLocationOpt.isEmpty()){
                throw new DaoException("Components for ride not found");
            }
            Client ride_client = rideClientOpt.get();
            Location ride_start_location = rideStartLocationOpt.get();
            Location ride_end_location = rideEndLocationOpt.get();
            double distance = set.getDouble("distance");
            LocalDateTime ride_start_time = set.getTimestamp("start_date").toLocalDateTime();
            if(rideDriverOpt.isEmpty()){
                ride = Ride.newBuilder().addId(ride_id).addClient(ride_client).addDistance(distance).addStartLocation(ride_start_location)
                        .addEndLocation(ride_end_location).addStartDate(ride_start_time).build();
                return ride;
            }
            Driver ride_driver = rideDriverOpt.get();
            Timestamp end_date = set.getTimestamp("end_date");
            LocalDateTime ride_end_time = null;
            if(end_date!=null){
                ride_end_time = end_date.toLocalDateTime();
            }
            Rating ride_client_mark = Rating.values()[set.getInt("client_mark") - 1];
            Rating ride_driver_mark = Rating.values()[set.getInt("driver_mark") - 1];
            ride = Ride.newBuilder().addId(ride_id).addClient(ride_client).addDriver(ride_driver).addDistance(distance).addStartLocation(ride_start_location)
                    .addEndLocation(ride_end_location).addStartDate(ride_start_time).addEndDate(ride_end_time)
                    .addClientMark(ride_client_mark).addDriverMark(ride_driver_mark).build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return ride;
    }
}
