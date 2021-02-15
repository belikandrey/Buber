package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.AbstractDao;
import com.epam.jwd.db.ConnectionPool;
import com.epam.jwd.domain.impl.Car;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.CarFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDao implements AbstractDao<Long, Car> {
    public static final CarDao CAR_DAO = new CarDao();

    private CarDao() {
    }

    private final ConnectionPool connectionPool = ConnectionPool.CONNECTION_POOL;

    private final String SQL_FIND_ALL_CARS = "select id,driver_id, number, brand, model,color  from car";
    private final String SQL_FIND_CAR_BY_ID = SQL_FIND_ALL_CARS + " where id = ?";
    private final String SQL_REMOVE_CAR_BY_ID = "delete from car where id = ?";
    private final String SQL_ADD_CAR = "insert into car(number,driver_id, brand, model, color) values(?,?,?,?,?)";
    private final String SQL_FIND_BY_DRIVER_ID = SQL_FIND_ALL_CARS + " where driver_id = ?";
    private final String SQL_UPDATE_BY_ID = "update car set number=?, brand = ?,model = ?, color=?  where id=?";


    @Override
    public List<Car> findAll() throws DaoException {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_CARS)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cars.add(createEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return cars;
    }

    @Override
    public Optional<Car> findEntityById(Long id) throws DaoException {
        Car car = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CAR_BY_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                car = createEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.ofNullable(car);
    }

    @Override
    public boolean update(Car entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID)) {

            preparedStatement.setString(1, entity.getNumber());
            preparedStatement.setString(2, entity.getBrand());
            preparedStatement.setString(3, entity.getModel());
            preparedStatement.setString(4, entity.getColor());
            preparedStatement.setLong(5, entity.getId());
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean remove(Car entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE_CAR_BY_ID)) {
            preparedStatement.setLong(1, entity.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }


    public List<Car> findByDriverId(Long id) throws DaoException {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_DRIVER_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car entity = createEntity(resultSet);
                cars.add(entity);
            }

        } catch (SQLException | DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return cars;
    }

    @Override
    public boolean add(Car entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_CAR)) {
            preparedStatement.setString(1, entity.getNumber());
            preparedStatement.setLong(2, entity.getDriver().getId());
            preparedStatement.setString(3, entity.getBrand());
            preparedStatement.setString(4, entity.getModel());
            preparedStatement.setString(5, entity.getColor());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public Car createEntity(ResultSet set) throws DaoException {
        try {
            long car_id = set.getLong("id");
            long driver_id = set.getLong("driver_id");
            String car_number = set.getString("number");
            String car_brand = set.getString("brand");
            String car_model = set.getString("model");
            String car_color = set.getString("color");
            Optional<Driver> driverOpt = DriverDao.DRIVER_DAO.findEntityById(driver_id);
            if (driverOpt.isEmpty()) {
                throw new DaoException("Driver for car with id : " + car_id + " not found");
            }
            Driver entityById = driverOpt.get();
            return CarFactory.getInstance().create(car_id, entityById, car_number, car_brand, car_model, car_color);
        } catch (SQLException | FactoryException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
