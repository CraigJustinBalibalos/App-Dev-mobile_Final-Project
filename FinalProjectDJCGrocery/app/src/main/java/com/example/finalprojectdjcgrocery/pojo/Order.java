package com.example.finalprojectdjcgrocery.pojo;

public class Order {
    String username, date, delivery, price;

    public Order() {
    }

    public Order(String date, String delivery, String price) {
        this.date = date;
        this.delivery = delivery;
        this.price = price;
    }

    public Order(String username, String date, String delivery, String price) {
        this.username = username;
        this.date = date;
        this.delivery = delivery;
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "username='" + username + '\'' +
                ", date='" + date + '\'' +
                ", delivery='" + delivery + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
