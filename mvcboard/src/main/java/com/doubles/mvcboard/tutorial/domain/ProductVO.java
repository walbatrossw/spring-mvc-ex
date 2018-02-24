package com.doubles.mvcboard.tutorial.domain;

public class ProductVO {

    private String name;
    private double price;

    public ProductVO(String name, double price) {
        super();
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ProductVO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
