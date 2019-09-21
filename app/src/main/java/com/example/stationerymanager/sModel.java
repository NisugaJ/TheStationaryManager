package com.example.stationerymanager;

public class sModel {
    private String ID;
    private String DATE;
    private String CODE;
    private String NAME;
    private String PRICE;
    private String QUANTITY;

    public sModel(String ID, String DATE, String CODE, String NAME, String PRICE, String QUANTITY) {
        this.ID = ID;
        this.DATE = DATE;
        this.CODE = CODE;
        this.NAME = NAME;
        this.PRICE = PRICE;
        this.QUANTITY = QUANTITY;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(String QUANTITY) {
        this.QUANTITY = QUANTITY;
    }
}
