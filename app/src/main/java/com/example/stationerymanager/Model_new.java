package com.example.stationerymanager;

public class Model_new {

    int ID;
    String ServiceName;
    String ServiceType;
    String Description;
    String CostPrice;
    String SellingPrice;
    String Quantity;



    public Model_new(int ID,String serviceName, String serviceType, String description, String costPrice, String sellingPrice, String quantity) {
        this.ID = ID;
        ServiceName = serviceName;
        ServiceType = serviceType;
        Description = description;
        CostPrice = costPrice;
        SellingPrice = sellingPrice;
        Quantity = quantity;


    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCostPrice() {
        return CostPrice;
    }

    public void setCostPrice(String costPrice) {
        CostPrice = costPrice;
    }

    public String getSellingPrice() {
        return SellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        SellingPrice = sellingPrice;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
