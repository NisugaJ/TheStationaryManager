package com.example.stationerymanager;

public class pModel {
    private int id;
    private String category;
    private String code;
    private String nmae;
    private String cPrice;
    private String sPrice;
    private String quantity;

    public pModel(int id, String category, String code, String nmae, String cPrice, String sPrice, String quantity) {
        this.id = id;
        this.category = category;
        this.code = code;
        this.nmae = nmae;
        this.cPrice = cPrice;
        this.sPrice = sPrice;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNmae() {
        return nmae;
    }

    public void setNmae(String nmae) {
        this.nmae = nmae;
    }

    public String getcPrice() {
        return cPrice;
    }

    public void setcPrice(String cPrice) {
        this.cPrice = cPrice;
    }

    public String getsPrice() {
        return sPrice;
    }

    public void setsPrice(String sPrice) {
        this.sPrice = sPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
