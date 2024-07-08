package com.academy.mbank.model;

public class User {
    private int id;
    private String username;
    private String phone;
    private String password;
    private String email;
    private int money;

    public User() {
    }

    public User(int id, String username, String phone, String password, String email, int money) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
