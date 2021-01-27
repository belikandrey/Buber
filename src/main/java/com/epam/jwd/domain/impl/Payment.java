package com.epam.jwd.domain.impl;

import com.epam.jwd.domain.Entity;

import java.util.Objects;

public class Payment extends Entity {
    private Ride ride;
    private PaymentType paymentType;
    private double price;
    private int transactionNumber;

    public Payment() {
    }

    public Payment(Ride ride, PaymentType paymentType, double price, int transactionNumber) {
        this.ride = ride;
        this.paymentType = paymentType;
        this.price = price;
        this.transactionNumber = transactionNumber;
    }

    public Payment(long id, Ride ride, PaymentType paymentType, double price, int transactionNumber) {
        super(id);
        this.ride = ride;
        this.paymentType = paymentType;
        this.price = price;
        this.transactionNumber = transactionNumber;
    }

    public Payment(Ride ride, double price){
        this.ride = ride;
        this.price = price;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.price, price) == 0 && transactionNumber == payment.transactionNumber && Objects.equals(ride, payment.ride) && paymentType == payment.paymentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ride, paymentType, price, transactionNumber);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "ride=" + ride +
                ", paymentType=" + paymentType +
                ", price=" + price +
                ", transactionNumber=" + transactionNumber +
                '}';
    }
}
