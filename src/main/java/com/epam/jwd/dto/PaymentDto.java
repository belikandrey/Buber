package com.epam.jwd.dto;


public class PaymentDto {
    private long id;
    private long rideId;
    private String paymentType;
    private double price;
    private int transactionNumber;

    public PaymentDto(long id, long rideId, String paymentType, double price, int transactionNumber) {
        this.id = id;
        this.rideId = rideId;
        this.paymentType = paymentType;
        this.price = price;
        this.transactionNumber = transactionNumber;
    }

    public long getId() {
        return id;
    }

    public long getRideId() {
        return rideId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public double getPrice() {
        return price;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }
}
