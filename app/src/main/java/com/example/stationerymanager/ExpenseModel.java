package com.example.stationerymanager;

public class ExpenseModel {
    int id;
    String type;
    String noteDescription;
    String timestamp;
    String amount;

    public ExpenseModel(int id, String type, String noteDescription, String timestamp, String amount) {
        this.id = id;
        this.type = type;
        this.noteDescription = noteDescription;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getAmount() {
        return amount;
    }
}
