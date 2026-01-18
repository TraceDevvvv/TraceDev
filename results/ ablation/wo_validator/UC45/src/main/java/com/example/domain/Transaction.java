package com.example.domain;

import java.util.Date;
import java.util.List;

/**
 * Represents a transaction at a refreshment point.
 */
public class Transaction {
    private String transactionId;
    private String pointId;
    private double amount;
    private Date timestamp;
    private List<String> items;

    public Transaction(String id, String pointId, double amount, Date timestamp) {
        this.transactionId = id;
        this.pointId = pointId;
        this.amount = amount;
        this.timestamp = timestamp;
        // Items can be set separately if needed
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getPointId() {
        return pointId;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}