package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.AbstractDao;
import com.epam.jwd.db.ConnectionPool;
import com.epam.jwd.domain.impl.Payment;
import com.epam.jwd.domain.impl.PaymentType;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.PaymentFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDao implements AbstractDao<Long, Payment> {
    public static final PaymentDao PAYMENT_DAO = new PaymentDao();

    private PaymentDao(){}

    private final ConnectionPool connectionPool = ConnectionPool.CONNECTION_POOL;

    private final String SQL_FIND_ALL_PAYMENTS = "select payment.id, ride_id, payment_type.name, price, transaction_number from payment" +
            " join payment_type on type_id = payment_type.id";
    private final String SQL_FIND_PAYMENT_BY_ID = SQL_FIND_ALL_PAYMENTS + " where payment.id = ?";
    private final String SQL_REMOVE_PAYMENT_BY_ID = "delete from payment where id = ?";
    private final String SQL_ADD_PAYMENT = "insert into payment(ride_id, type_id, price, transaction_number) values(?,?,?,?);";
    private final String SQL_FIND_PAYMENT_BY_RIDE_ID = SQL_FIND_ALL_PAYMENTS + " where ride_id = ?";
    private final String SQL_UPDATE_PAYMENT = "update payment set ride_id=?, type_id = ?, price = ?, transaction_number = ?  where id=?";

    @Override
    public List<Payment> findAll() throws DaoException {
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_PAYMENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                payments.add(createEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return payments;
    }

    @Override
    public Optional<Payment> findEntityById(Long id) throws DaoException {
        Payment payment = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_PAYMENT_BY_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                payment = createEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(payment);
    }

    @Override
    public boolean update(Payment entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PAYMENT)) {
            preparedStatement.setLong(1, entity.getRide().getId());
            preparedStatement.setLong(2, entity.getPaymentType().ordinal());
            preparedStatement.setDouble(3, entity.getPrice());
            preparedStatement.setLong(4, entity.getTransactionNumber());
            preparedStatement.setLong(5, entity.getId());
            return (preparedStatement.executeUpdate()>0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public Optional<Payment> findPaymentByRideId(Long id) throws DaoException {
        Payment payment = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_PAYMENT_BY_RIDE_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                payment = createEntity(resultSet);
            }
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(payment);
    }

    @Override
    public boolean remove(Payment entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE_PAYMENT_BY_ID)) {
            preparedStatement.setLong(1, entity.getId());
            return (preparedStatement.executeUpdate()>0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean add(Payment entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_PAYMENT)) {
            System.out.println("CONNECTION AC : "+connection.getAutoCommit());
            preparedStatement.setLong(1, entity.getRide().getId());
            preparedStatement.setLong(2, entity.getPaymentType().ordinal()+1);
            preparedStatement.setDouble(3, entity.getPrice());
            preparedStatement.setLong(4, entity.getTransactionNumber());
            return (preparedStatement.executeUpdate()>0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Payment createEntity(ResultSet set) throws DaoException {
        Payment payment = null;
        try {
            long payment_id = set.getLong("id");
            long ride_id = set.getLong("ride_id");
            Optional<Ride> rideOpt = RideDAO.RIDE_DAO.findEntityById(ride_id);
            if(rideOpt.isEmpty()){
                throw new DaoException("Ride for payment with id : "+payment_id+" not found");
            }
            Ride ride = rideOpt.get();
            PaymentType payment_type = PaymentType.valueOf(set.getString("payment_type.name"));
            double payment_price = set.getDouble("price");
            int payment_transaction_number = set.getInt("transaction_number");
            payment = PaymentFactory.getInstance().create(payment_id,ride, payment_type, payment_price, payment_transaction_number);
        } catch (SQLException | FactoryException e) {
            throw new DaoException(e);
        }
        return payment;
    }
}
