package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.AbstractDao;
import com.epam.jwd.db.ConnectionPool;
import com.epam.jwd.domain.impl.User;
import com.epam.jwd.domain.impl.UserRole;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.UserFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements AbstractDao<Long, User> {
    public static final UserDao USER_DAO = new UserDao();

    private UserDao(){}

    private final ConnectionPool connectionPool = ConnectionPool.CONNECTION_POOL;

    private final String SQL_FIND_ALL_USERS = "select id, login, password from app_user";
    private final String SQL_FIND_ROLES_BY_USER_ID = "select name from app_user_user_role inner join user_role on user_role.id = app_user_user_role.role_id where user_id = ?";
    private final String SQL_FIND_USER_BY_ID = SQL_FIND_ALL_USERS + " where id = ?";
    private final String SQL_UPDATE_USER = "update app_user set login=?, password = ? where id=?";
    private final String SQL_REMOVE_USER_BY_LOGIN = "delete from app_user where login = ?";
    private final String SQL_REMOVE_ROLE = "delete from app_user_user_role where user_id = ? and role_id = ?";
    private final String SQL_INSERT_ROLE = "insert into app_user_user_role(user_id,role_id) values(?,?)";
    private final String SQL_ADD_USER = "insert into app_user(login, password) values(?,?);";
    private final String SQL_FIND_USER_BY_LOGIN = SQL_FIND_ALL_USERS+" where login = ?";


    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                users.add(createEntity(resultSet));
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }
        return users;
    }



    @Override
    public Optional<User> findEntityById(Long id) throws DaoException {
        User user = null;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                user = createEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean update(User entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());

            preparedStatement.setLong(3, entity.getId());
            return (preparedStatement.executeUpdate()>0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean remove(User entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE_USER_BY_LOGIN)) {
            preparedStatement.setString(1, entity.getLogin());
            if(preparedStatement.executeUpdate()>0){
                for(UserRole role : entity.getRoles()){
                    removeRole(role, entity.getId());
                }
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return false;
    }

    private boolean removeRole(UserRole role, Long userId) throws DaoException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_ROLE)){
            statement.setLong(1, userId);
            statement.setLong(2, role.ordinal()+1);
            return (statement.executeUpdate()>0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public boolean add(User entity, Connection connection) throws DaoException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER)){
            fillPreparedStatementToAdd(entity, preparedStatement);
            if(preparedStatement.executeUpdate()>0){
                Optional<User> userOptional = findByLogin(entity.getLogin(), connection);
                for(UserRole role : entity.getRoles()){
                    addRole(role, userOptional.get().getId(), connection);
                }
                entity.setId(userOptional.get().getId());
                return true;
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }
        return false;
    }

    private void fillPreparedStatementToAdd(User entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getPassword());
    }

    @Override
    public boolean add(User entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER)) {
            fillPreparedStatementToAdd(entity, statement);

            if(statement.executeUpdate()>0){
                for(UserRole role : entity.getRoles()){
                    addRole(role, findByLogin(entity.getLogin()).get().getId());
                }
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return false;
    }


    public Optional<User> findByLogin(String login, Connection connection) throws DaoException {
        User user = null;
        try(PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)){
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = createEntity(resultSet);
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> findByLogin(String login) throws DaoException {
        User user = null;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = createEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }


    public boolean addRole(UserRole role, Long userId, Connection connection) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ROLE)){
            fillPreparedStatementToAddRole(role, userId, statement);
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void fillPreparedStatementToAddRole(UserRole role, Long userId, PreparedStatement statement) throws SQLException {
        statement.setLong(1, userId);
        statement.setLong(2, role.ordinal()+1);
    }

    public boolean addRole(UserRole role, Long userId) throws DaoException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ROLE)){
            fillPreparedStatementToAddRole(role, userId, statement);
            return (statement.executeUpdate()>0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public User createEntity(ResultSet resultSet) throws  DaoException {
        User user = null;
        try {
            long id = resultSet.getLong("id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            List<UserRole> roles = findRolesByUserId(id);
            user = UserFactory.getInstance().create(id, login, password, roles);
        } catch (SQLException | FactoryException e) {
            throw new DaoException(e);
        }
        return user;
    }


    public List<UserRole> findRolesByUserId(Long id) throws DaoException {
        List<UserRole> userRoles = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_FIND_ROLES_BY_USER_ID)){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                userRoles.add(createRole(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userRoles;
    }


    private UserRole createRole(ResultSet resultSet) throws DaoException {
        try{
            return UserRole.valueOf(resultSet.getString(1));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
