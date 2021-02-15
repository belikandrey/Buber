package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.AbstractDao;
import com.epam.jwd.db.ConnectionPool;
import com.epam.jwd.domain.impl.Location;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.LocationFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationDao implements AbstractDao<Long, Location> {

    public static final LocationDao LOCATION_DAO = new LocationDao();

    private LocationDao() {
    }

    private final ConnectionPool connectionPool = ConnectionPool.CONNECTION_POOL;

    private final String SQL_FIND_ALL_LOCATIONS = "select id, latitude, longitude, address from location";
    private final String SQL_FIND_LOCATION_BY_ID = SQL_FIND_ALL_LOCATIONS + " where id = ?";
    private final String SQL_UPDATE_LOCATION = "update location set latitude = ?, longitude=?, address=? where id=?";
    private final String SQL_REMOVE_LOCATION_BY_ID = "delete from location where id = ?";
    private final String SQL_ADD_LOCATION = "insert into location(latitude, longitude, address) values( ? , ?, ?)";
    private final String SQL_FIND_BY_LATITUDE_LONGITUDE = SQL_FIND_ALL_LOCATIONS + " where latitude=? and longitude=?";


    @Override
    public List<Location> findAll() throws DaoException {
        List<Location> locations = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_LOCATIONS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                locations.add(createEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return locations;
    }

    @Override
    public Optional<Location> findEntityById(Long id) throws DaoException {
        Location location = null;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_LOCATION_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                location = createEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.ofNullable(location);
    }


    public Optional<Location> findEntityByCoords(double latitude, double logitude) throws DaoException {
        Location location = null;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_LATITUDE_LONGITUDE)) {
            preparedStatement.setDouble(1, latitude);
            preparedStatement.setDouble(2, logitude);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                location = createEntity(resultSet);
            }
        } catch (SQLException | DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.ofNullable(location);
    }

    @Override
    public boolean update(Location entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_LOCATION)) {
            preparedStatement.setDouble(1, entity.getLatitude());
            preparedStatement.setDouble(2, entity.getLongitude());
            preparedStatement.setString(3, entity.getAddress());
            preparedStatement.setLong(4, entity.getId());
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean remove(Location entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE_LOCATION_BY_ID)) {
            preparedStatement.setLong(1, entity.getId());
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean add(Location entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_LOCATION)) {
            preparedStatement.setDouble(1, entity.getLatitude());
            preparedStatement.setDouble(2, entity.getLongitude());
            preparedStatement.setString(3, entity.getAddress());
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Location createEntity(ResultSet set) throws DaoException {
        try {
            long location_id = set.getLong("id");
            double location_latitude = set.getDouble("latitude");
            double location_longitude = set.getDouble("longitude");
            String address = set.getString("address");
            return LocationFactory.getInstance().create(location_id, location_latitude, location_longitude, address);
        } catch (SQLException | FactoryException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
