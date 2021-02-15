package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.AbstractDao;
import com.epam.jwd.db.ConnectionPool;
import com.epam.jwd.domain.impl.BankCard;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.BankCardFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankCardDao implements AbstractDao<Long, BankCard> {

    public static final BankCardDao BANK_CARD_DAO = new BankCardDao();

    private BankCardDao() {
    }

    private ConnectionPool connectionPool = ConnectionPool.CONNECTION_POOL;

    private final String SQL_FIND_ALL_CARDS = "select id, client_id, number, date, csc from bank_card";
    private final String SQL_FIND_CARD_BY_ID = SQL_FIND_ALL_CARDS + " where id = ?";
    private final String SQL_REMOVE_CARD_BY_ID = "delete from bank_card where id = ?";
    private final String SQL_ADD_CARD = "insert into bank_card(client_id, number, date, csc) values(?,?,?,?);";
    private final String SQL_FIND_CARD_BY_CLIENT_ID = SQL_FIND_ALL_CARDS + " where client_id = ?";
    private final String SQL_UPDATE_CARD = "update bank_card set number=?, date = ?,csc = ?  where id=?";


    @Override
    public List<BankCard> findAll() throws DaoException {
        List<BankCard> bankCards = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_CARDS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bankCards.add(createEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return bankCards;
    }

    @Override
    public Optional<BankCard> findEntityById(Long id) throws DaoException {
        BankCard bankCard = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CARD_BY_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bankCard = createEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.ofNullable(bankCard);
    }

    @Override
    public boolean update(BankCard entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CARD)) {

            preparedStatement.setString(1, entity.getNumber());
            preparedStatement.setString(2, entity.getDate());
            preparedStatement.setInt(3, entity.getCsc());
            preparedStatement.setLong(4, entity.getId());
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public List<BankCard> findByClientId(Long id) throws DaoException {
        List<BankCard> bankCards = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CARD_BY_CLIENT_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bankCards.add(createEntity(resultSet));
            }
        } catch (SQLException | DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return bankCards;
    }

    @Override
    public boolean remove(BankCard entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE_CARD_BY_ID)) {

            preparedStatement.setLong(1, entity.getId());
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean add(BankCard entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_CARD)) {

            preparedStatement.setLong(1, entity.getClient().getId());
            preparedStatement.setString(2, entity.getNumber());
            preparedStatement.setString(3, entity.getDate());
            preparedStatement.setInt(4, entity.getCsc());
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public BankCard createEntity(ResultSet set) throws DaoException {
        try {
            long card_id = set.getLong("id");
            long client_id = set.getLong("client_id");
            String card_number = set.getString("number");
            String card_date = set.getString("date");
            int card_csc = set.getInt("csc");
            Optional<Client> clientOpt = ClientDao.CLIENT_DAO.findEntityById(client_id);
            if (clientOpt.isEmpty()) {
                throw new DaoException("Client for bank card with id : " + card_id + " not found");
            }
            Client entityById = clientOpt.get();
            return BankCardFactory.getInstance().create(card_id, entityById, card_number, card_date, card_csc);
        } catch (SQLException | DaoException | FactoryException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
