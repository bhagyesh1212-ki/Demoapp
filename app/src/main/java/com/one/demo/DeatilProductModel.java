package com.one.demo;

public class DeatilProductModel {

    private int product;
    private String product_title;
    private String product_desc;
    private int product_price;

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public DeatilProductModel(int product, String product_title, String product_desc, int product_price) {
        this.product = product;
        this.product_title = product_title;
        this.product_desc = product_desc;
        this.product_price = product_price;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }



    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }
}
