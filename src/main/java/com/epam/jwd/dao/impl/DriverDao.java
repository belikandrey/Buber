package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.AbstractDao;
import com.epam.jwd.db.ConnectionPool;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.domain.impl.DriverStatus;
import com.epam.jwd.domain.impl.Rating;
import com.epam.jwd.domain.impl.UserRole;
import com.epam.jwd.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DriverDao implements AbstractDao<Long, Driver> {
    public static final DriverDao DRIVER_DAO = new DriverDao();

    private DriverDao(){
    }

    private final ConnectionPool connectionPool = ConnectionPool.CONNECTION_POOL;

    private final String SQL_FIND_ALL_DRIVERS = "select app_driver.id, app_driver.name, phone_number, email, " +
            " rating, driver_status.name, login, password " +
            "from app_driver join driver_status on driver_status.id = app_driver.status_id " +
            "join app_user on app_user.id = app_driver.id";
    private final String SQL_FIND_DRIVER_BY_ID = SQL_FIND_ALL_DRIVERS + " where app_driver.id = ?";
    private final String SQL_FIND_DRIVER_BY_LOGIN = SQL_FIND_ALL_DRIVERS+" where login=?";
    private final String SQL_ADD_DRIVER = "insert into app_driver(id, name, phone_number, email, rating, status_id) values(?,?,?,?,?,?);";
    private final String SQL_UPDATE_DRIVER = "update app_driver set name=?, phone_number = ?, rating = ?, email = ?, status_id=?  where id=?";
    private final String SQL_UPDATE_DRIVER_STATUS = "update app_driver set status_id = ? where id = ?";
    private final String SQL_UPDATE_DRIVER_RATING = "update app_driver set rating=? where id=?";
    private final String SQL_UPDATE_EMAIL = "update app_driver set email = ? where id = ?";
    private final String SQL_UPDATE_PHONE_NUMBER = "update app_driver set phone_number = ? where id = ?";

    @Override
    public List<Driver> findAll() throws DaoException {
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_DRIVERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                drivers.add(createEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return drivers;
    }


    public void updatePhoneNumber(Long id, String newPhoneNumber) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PHONE_NUMBER) ){
            preparedStatement.setString(1, newPhoneNumber);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Driver> findEntityById(Long id) throws DaoException {
        Driver driver = null;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_DRIVER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                driver = createEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(driver);
    }


    public boolean updateStatus(String login, DriverStatus status) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DRIVER_STATUS)){
            Optional<Driver> driver = findByLogin(login);
            if(driver.isPresent()){
                statement.setInt(1, status.ordinal()+1);
                statement.setLong(2, driver.get().getId());
                return (statement.executeUpdate()>0);
            }
            return false;
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }

    public boolean updateRating(String login, int rating) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DRIVER_RATING)){
            Optional<Driver> driver = findByLogin(login);
            if(driver.isPresent()){
                statement.setInt(1, rating);
                statement.setLong(2, driver.get().getId());
                return statement.executeUpdate()>0;
            }
            return false;
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }


    public Optional<Driver> findByLogin(String login) throws DaoException {
        Driver driver = null;
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_DRIVER_BY_LOGIN)){
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                driver = createEntity(resultSet);
            }
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(driver);
    }


    public boolean updateEmail(String login, String email) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_EMAIL)){
            Optional<Driver> driver = findByLogin(login);
            if(driver.isPresent()){
                preparedStatement.setString(1, email);
                preparedStatement.setLong(2, driver.get().getId());
                return (preparedStatement.executeUpdate()>0);
            }
            return false;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public boolean update(Driver entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_DRIVER)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPhoneNumber());
            preparedStatement.setInt(3, entity.getRating().ordinal() + 1);
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setInt(5, entity.getStatus().ordinal() + 1);
            preparedStatement.setLong(6, entity.getId());
            return (preparedStatement.executeUpdate()>0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Connection getConnection(){
        return connectionPool.getConnection();
    }

    @Override
    public boolean remove(Driver entity) throws DaoException {
        return UserDao.USER_DAO.remove(entity);
    }

    public boolean add(Driver entity, Connection connection) throws DaoException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_DRIVER)){
            System.out.println("ADDING IN DAO : "+entity);
            fillPreparedStatementToAdd(entity, preparedStatement);
            return preparedStatement.executeUpdate()>0;
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }

    private void fillPreparedStatementToAdd(Driver entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, entity.getId());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getPhoneNumber());
        preparedStatement.setString(4, entity.getEmail());
        preparedStatement.setInt(5, entity.getRating().ordinal() + 1);
        preparedStatement.setInt(6, entity.getStatus().ordinal() + 1);
    }

    @Override
    public boolean add(Driver entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_DRIVER)) {
            fillPreparedStatementToAdd(entity, preparedStatement);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public Driver createEntity(ResultSet set) throws DaoException {
        Driver driver = null;
        try {
            long driver_id = set.getLong("id");
            String driver_name = set.getString("name");
            String driver_phone_number = set.getString("phone_number");
            Rating rating = Rating.values()[set.getInt("rating") - 1];
            String driver_email = set.getString("email");
            DriverStatus driver_status = DriverStatus.valueOf(set.getString("driver_status.name"));
            String user_login = set.getString("login");
            String user_password = set.getString("password");
            List<UserRole> roles = UserDao.USER_DAO.findRolesByUserId(driver_id);

            driver = Driver.newBuilder().addRoles(roles).addId(driver_id).addName(driver_name).addPhoneNumber(driver_phone_number)
                    .addRating(rating).addStatus(driver_status).addEmail(driver_email).addLogin(user_login).addPassword(user_password).build();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return driver;
    }
}
