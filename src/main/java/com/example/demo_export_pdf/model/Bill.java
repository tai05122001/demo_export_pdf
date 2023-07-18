package com.example.demo_export_pdf.model;

import java.time.LocalDate;
import java.util.Random;

public class Bill {
    private int idBill;
    private String dateBill;
    private String nameBill;
    private String urlBill;
    private String nameCustomer;

    public String getUrlBill() {
        return urlBill;
    }

    public void setUrlBill(String urlBill) {
        this.urlBill = urlBill;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    private String addressCustomer;


    public String getUrlFile() {
        return urlBill;
    }

    public void setUrlFile(String urlFile) {
        this.urlBill = urlFile;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public String getDateBill() {
        return dateBill;
    }

    public void setDateBill(String dateBill) {
        this.dateBill = dateBill;
    }

    public String getNameBill() {
        return nameBill;
    }

    public void setNameBill(String nameBill) {
        this.nameBill = nameBill;
    }

    public Bill(){
        Random r = new Random();
        this.idBill = r.nextInt(100);
        this.dateBill = LocalDate.now().toString();
        this.nameBill = "Bill sell of ABC store";


    }

    @Override
    public String toString() {
        return " ID: " + this.idBill + "\n Name: "+ this.nameBill + " \nDate of bill: "+ this.dateBill +"\n Name customer" + this.nameCustomer + "\n Address customer:" + this.addressCustomer;
    }
}
