package com.example.finalprojectdjcgrocery;

public class CartItem {
    String imgUrl, pName, price, qty;

    public CartItem(String imgUrl, String pName, String price, String qty) {
        this.imgUrl = imgUrl;
        this.pName = pName;
        this.price = price;
        this.qty = qty;
    }

    public CartItem() {
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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "imgUrl='" + imgUrl + '\'' +
                ", pName='" + pName + '\'' +
                ", price='" + price + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}
