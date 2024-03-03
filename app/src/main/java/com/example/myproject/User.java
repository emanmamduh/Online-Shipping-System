package com.example.myproject;

public class User {
    private int id;
    private String email;
    private String username;
    private String phone;
    private String card;
    private String cvc;
    private String year;

    public User(int id, String email,String username,String phone,String card,String cvc,String year) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.card = card;
        this.cvc = cvc;
        this.year = year;
    }

    public User(int id, String email,String username,String phone) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.phone = phone;
    }
    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public String getPhone() {
        return phone;
    }

    public String getcard() {
        return card;
    }

    public String getcvc() {
        return cvc;
    }

    public String getyear() {
        return year;
    }

}
