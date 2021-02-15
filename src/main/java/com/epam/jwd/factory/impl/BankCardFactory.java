package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.impl.BankCard;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.AbstractFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankCardFactory implements AbstractFactory<BankCard> {

    private BankCardFactory() {
    }

    private static BankCardFactory bankCardFactory;

    public static BankCardFactory getInstance() {
        if (bankCardFactory == null) {
            bankCardFactory = new BankCardFactory();
        }
        return bankCardFactory;
    }

    @Override
    public BankCard create(Object... args) throws FactoryException {
        switch (args.length) {
            case 5:
                return new BankCard((long) args[0], (Client) args[1], (String) args[2], (String) args[3], (int) args[4]);
            case 4:
                return new BankCard((Client) args[0], (String) args[1], (String) args[2], (int) args[3]);
            default:
                throw new FactoryException("Bank card factory : Illegal arguments length : " + args.length);
        }
    }
}
