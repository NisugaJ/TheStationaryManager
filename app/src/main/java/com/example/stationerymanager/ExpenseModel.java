package com.example.stationerymanager;

import java.util.ArrayList;

public class ExpenseModel {
    int id;
    String title;
    String type;
    String noteDescription;
    String timestamp;
    String amount;


    public ExpenseModel(int id, String title, String type, String amount , String timestamp, String noteDescription) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.noteDescription = noteDescription;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getTitle() { return title; }

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

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String[] splitDateTime(){
        if (timestamp == "" || timestamp == null){
            timestamp = "0000-00-00 00:00";
        }
        String[] parts = timestamp.split(" ");
        return  parts;
    }


}
