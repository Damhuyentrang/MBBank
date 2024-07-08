package com.academy.mbank;

public class TypeSwapMoney {
    private int id;

    private String phone;
    private String typeSwap;
    private String date;
    private String accountTo;
    private String content;
    private int price;

    public TypeSwapMoney(int id,String phone, String typeSwap, String date, String accountTo, String content, int price) {
        this.id = id;
        this.phone = phone;
        this.typeSwap = typeSwap;
        this.date = date;
        this.accountTo = accountTo;
        this.content = content;
        this.price = price;
    }

    public TypeSwapMoney() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTypeSwap() {
        return typeSwap;
    }

    public void setTypeSwap(String typeSwap) {
        this.typeSwap = typeSwap;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
