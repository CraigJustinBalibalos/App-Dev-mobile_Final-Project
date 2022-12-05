package com.example.finalprojectdjcgrocery;

public class Order {
    String date, delivery, price;

    public Order() {
    }

    public Order(String date, String delivery, String price) {
        this.date = date;
        this.delivery = delivery;
        this.price = price;
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
                "date='" + date + '\'' +
                ", delivery='" + delivery + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
