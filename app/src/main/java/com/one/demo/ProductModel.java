package com.one.demo;

public class ProductModel {
    private  int product;
    private String productName;
    private  int product_price;

    public ProductModel(int product, String productName, int product_price) {
        this.product = product;
        this.productName = productName;
        this.product_price = product_price;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }
}
