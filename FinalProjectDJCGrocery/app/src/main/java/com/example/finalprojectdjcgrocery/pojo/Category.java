package com.example.finalprojectdjcgrocery.pojo;

public class Category {

    String name;
    String img;

    public Category() {
    }

    public Category(String name, String img) {
        this.name = name;
        this.img = img;
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
