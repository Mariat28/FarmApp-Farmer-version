package com.example.glosales.Adapterclasses;

public class Orderclass {
    private String clientname;
    private String clientcontact;
    private String orderdetails;

    public Orderclass(String clientname, String clientcontact, String orderdetails) {
        this.clientname = clientname;
        this.clientcontact = clientcontact;
        this.orderdetails = orderdetails;
    }

    public String getClientname() {
        return clientname;
    }

    public String getClientcontact() {
        return clientcontact;
    }

    public String getOrderdetails() {
        return orderdetails;
    }
}
