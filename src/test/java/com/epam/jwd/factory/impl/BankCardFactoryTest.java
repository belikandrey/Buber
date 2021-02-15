package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.impl.BankCard;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.exception.FactoryException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankCardFactoryTest {
    private Client client;
    private BankCard bankCard;
    private static final String CARD_NUMBER = "4255200043125678";
    private static final String CARD_DATE = "11/23";
    private static final int CARD_CSC = 123;
    private static final int SECOND_CAR_CSC = 333;
    private final BankCardFactory bankCardFactory = BankCardFactory.getInstance();

    @Before
    public void initCard(){
        client = Client.newBuilder().build();
        bankCard = new BankCard(client, CARD_NUMBER, CARD_DATE, CARD_CSC);
    }

    @Test
    public void rightEqualsCardTest() throws FactoryException {
        BankCard testCard = bankCardFactory.create(client, CARD_NUMBER, CARD_DATE, CARD_CSC);
        assertEquals(this.bankCard, testCard);
    }

    @Test(expected = FactoryException.class)
    public void wrongCardTest() throws FactoryException {
        bankCardFactory.create(CARD_DATE, CARD_CSC);
        Assert.fail("Expected exception");
    }

    @Test
    public void wrongEqualsCardTest() throws FactoryException {
        BankCard testCard = bankCardFactory.create(client, CARD_NUMBER, CARD_DATE, SECOND_CAR_CSC);
        assertNotEquals(testCard, bankCard);
    }

}
