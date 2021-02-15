package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.impl.Payment;
import com.epam.jwd.domain.impl.PaymentType;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.FactoryException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentFactoryTest {
    private final Ride ride = Ride.newBuilder().build();
    private final PaymentType paymentType = PaymentType.CASH;
    private final PaymentType secondPaymentType = PaymentType.CARD;
    private final double price = 5.3;
    private final int transactionNumber = 12356778;
    private final Payment payment = new Payment(ride, paymentType, price, transactionNumber);
    private final PaymentFactory paymentFactory = PaymentFactory.getInstance();

    @Test
    public void rightEqualsPaymentTest() throws FactoryException {
        Payment testPayment = paymentFactory.create(ride, paymentType, price, transactionNumber);
        assertEquals(testPayment, payment);
    }

    @Test
    public void wrongEqualsPaymentTest() throws FactoryException {
        Payment testPayment = paymentFactory.create(ride, secondPaymentType, price, transactionNumber);
        assertNotEquals(testPayment, payment);
    }

    @Test(expected = FactoryException.class)
    public void wrongPaymentTest() throws FactoryException {
        paymentFactory.create(secondPaymentType, price, transactionNumber);
        Assert.fail("Expected exception");
    }
}
