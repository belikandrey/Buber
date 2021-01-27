package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.impl.Payment;
import com.epam.jwd.domain.impl.PaymentType;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.AbstractFactory;

public class PaymentFactory implements AbstractFactory<Payment> {

    private PaymentFactory(){}

    private static PaymentFactory paymentFactory;

    public static PaymentFactory getInstance(){
        if(paymentFactory == null){
            paymentFactory = new PaymentFactory();
        }
        return paymentFactory;
    }

    @Override
    public Payment create(Object... args) throws FactoryException {
        switch (args.length){
            case 2:
                return new Payment((Ride)args[0], (double) args[1]);
            case 5:
                return new Payment((long) args[0], (Ride)args[1], (PaymentType) args[2], (double) args[3], (int)args[4]);
            case 4:
                return new Payment((Ride)args[0], (PaymentType) args[1], (double) args[2], (int)args[3]);
            default:
                throw new FactoryException("Payment factory : Illegal arguments length : "+args.length);
        }
    }
}
