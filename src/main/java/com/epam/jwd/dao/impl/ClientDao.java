package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.AbstractDao;
import com.epam.jwd.db.ConnectionPool;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.ClientStatus;
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

public class ClientDao implements AbstractDao<Long, Client> {
    public static final ClientDao CLIENT_DAO = new ClientDao();

    private ClientDao() {
    }

    private final ConnectionPool connectionPool = ConnectionPool.CONNECTION_POOL;

    private final String SQL_FIND_ALL_CLIENTS = "select app_client.id, app_client.name, phone_number, app_user.login, app_user.password," +
            " rating, email, client_status.name, bonus_percent, count_ride " +
            "from app_client join app_user on app_user.id = app_client.id " +
            " join client_status on app_client.status_id = client_status.id";
    private final String SQL_FIND_CLIENT_BY_ID = SQL_FIND_ALL_CLIENTS + " where app_client.id=?";
    private final String SQL_UPDATE_CLIENT = "update app_client set name = ?, phone_number = ?,rating = ?, email = ?, status_id = ?, bonus_percent=?, count_ride = ?  where client_id=?";
    private final String SQL_UPDATE_EMAIL = "update app_client set email = ? where app_user.login=?";
    private final String SQL_FIND_CLIENT_BY_LOGIN = SQL_FIND_ALL_CLIENTS + " where app_user.login = ?";
    private final String SQL_UPDATE_PHONE_NUMBER = "update app_client set phone_number = ? where id = ?";
    private final String SQL_UPDATE_CLIENT_RATING = "update app_client set rating = ? where id = ?";
    private final String SQL_UPDATE_CLIENT_STATUS = "update app_client set status_id = ? where app_client.id = ? ";
    private final String SQL_ADD_CLIENT = "insert into app_client(id, name, phone_number, email, rating, status_id, bonus_percent, count_ride) values(?,?,?,?,?,?,?,?);";


    @Override
    public List<Client> findAll() throws DaoException {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_CLIENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(createEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return clients;
    }

    public boolean updateRating(String login, int rating) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CLIENT_RATING)) {
            Optional<Client> client = findByLogin(login);
            if (client.isPresent()) {
                statement.setInt(1, rating);
                statement.setLong(2, client.get().getId());
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return false;
    }

    public void updatePhoneNumber(Long id, String phoneNumber) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PHONE_NUMBER)) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }


    public boolean updateEmail(String login, String email) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_EMAIL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, login);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Optional<Client> findEntityById(Long id) throws DaoException {
        Client client = null;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CLIENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                client = createEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.ofNullable(client);
    }

    @Override
    public boolean update(Client entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CLIENT)) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPhoneNumber());
            preparedStatement.setInt(3, entity.getRating().ordinal() + 1);
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setInt(5, entity.getStatus().ordinal() + 1);
            preparedStatement.setInt(6, entity.getBonusPercent());
            preparedStatement.setInt(7, entity.getCountRide());
            preparedStatement.setLong(8, entity.getId());
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean remove(Client entity) throws DaoException {
        return UserDao.USER_DAO.remove(entity);
    }


    public Optional<Client> findByLogin(String login) throws DaoException {
        Client client = null;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CLIENT_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                client = createEntity(resultSet);
            }
        } catch (SQLException | DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.ofNullable(client);
    }

    public boolean updateStatus(String login, ClientStatus clientStatus) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CLIENT_STATUS)) {
            Optional<Client> client = findByLogin(login);
            if (client.isPresent()) {
                preparedStatement.setInt(1, clientStatus.ordinal() + 1);
                preparedStatement.setLong(2, client.get().getId());
                return (preparedStatement.executeUpdate() > 0);
            }
        } catch (SQLException | DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return false;
    }

    public Connection getConnection() {
        return connectionPool.getConnection();
    }

    public boolean add(Client entity, Connection connection) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_CLIENT)) {
            fillPreparedStatementToAdd(entity, preparedStatement);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    private void fillPreparedStatementToAdd(Client entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, entity.getId());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getPhoneNumber());
        preparedStatement.setString(4, entity.getEmail());
        preparedStatement.setInt(5, entity.getRating().ordinal() + 1);
        preparedStatement.setInt(6, entity.getStatus().ordinal() + 1);
        preparedStatement.setInt(7, entity.getBonusPercent());
        preparedStatement.setInt(8, entity.getCountRide());
    }

    @Override
    public boolean add(Client entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_CLIENT)) {
            fillPreparedStatementToAdd(entity, preparedStatement);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public Client createEntity(ResultSet resultSet) throws DaoException {
        try {
            long client_id = resultSet.getLong("id");
            String user_login = resultSet.getString("app_user.login");
            String user_password = resultSet.getString("app_user.password");
            String client_name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String client_phone_number = resultSet.getString("phone_number");
            Rating client_rating = Rating.values()[resultSet.getInt("rating") - 1];
            ClientStatus client_status = ClientStatus.valueOf(resultSet.getString("client_status.name"));
            int client_bonus_percent = resultSet.getInt("bonus_percent");
            int client_count_ride = resultSet.getInt("count_ride");
            List<UserRole> roles = UserDao.USER_DAO.findRolesByUserId(client_id);
            return Client.newBuilder().addRoles(roles).addId(client_id).addLogin(user_login).addPassword(user_password).addName(client_name)
                    .addEmail(email).addPhoneNumber(client_phone_number).addRating(client_rating).addStatus(client_status)
                    .addBonus(client_bonus_percent).addCountRide(client_count_ride).build();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
