package com.example.finalprojectdjcgrocery.pojo;

public class CartItem {
    String key, imgUrl, pName, price, username;
    int qty;
    double total_price;

    public CartItem(String key, String imgUrl, String pName, String price, String username, int qty, double total_price) {
        this.key = key;
        this.imgUrl = imgUrl;
        this.pName = pName;
        this.price = price;
        this.username = username;
        this.qty = qty;
        this.total_price = total_price;
    }

    public CartItem() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "key='" + key + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", pName='" + pName + '\'' +
                ", price='" + price + '\'' +
                ", username='" + username + '\'' +
                ", qty=" + qty +
                ", total_price=" + total_price +
                '}';
    }
}
