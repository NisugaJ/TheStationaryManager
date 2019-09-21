package com.example.stationerymanager;

public class Model {

    private int id;
    private String Date;
    private String ServiceName;
    private String Category;
    private String price;
    private String quantity;
    private String profit;



    public Model(int id,String Date, String serviceName, String category, String price, String quantity, String profit) {
        this.id = id;
        this.Date = Date;
        ServiceName = serviceName;
        Category = category;
        this.price = price;
        this.quantity = quantity;
        this.profit = profit;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

   public String getDate() {
        return Date;
    }

    public void setDate(String date) {
       this.Date = date;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }
}
