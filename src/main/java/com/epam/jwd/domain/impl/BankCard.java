package com.epam.jwd.domain.impl;

import com.epam.jwd.domain.Entity;

import java.util.Objects;

public class BankCard extends Entity {
    private Client client;
    private String number;
    private String date;
    private int csc;

    public BankCard() {
    }

    public BankCard(Client client, String number, String date, int csc) {
        this.client = client;
        this.number = number;
        this.date = date;
        this.csc = csc;
    }

    public BankCard(long id, Client client, String number, String date, int csc) {
        super(id);
        this.client = client;
        this.number = number;
        this.date = date;
        this.csc = csc;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCsc() {
        return csc;
    }

    public void setCsc(int csc) {
        this.csc = csc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankCard bankCard = (BankCard) o;
        return csc == bankCard.csc && Objects.equals(client, bankCard.client) && Objects.equals(number, bankCard.number) && Objects.equals(date, bankCard.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, number, date, csc);
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "client=" + client +
                ", number='" + number + '\'' +
                ", date='" + date + '\'' +
                ", csc=" + csc +
                '}';
    }
}
