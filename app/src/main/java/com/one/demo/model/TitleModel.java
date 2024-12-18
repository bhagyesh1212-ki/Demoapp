package com.one.demo.model;

import java.util.List;

public class TitleModel {

    String Titlre;
    List<ProductModel> productModelArrayList;

    public TitleModel(String titlre, List<ProductModel> productModelArrayList) {
        Titlre = titlre;
        this.productModelArrayList = productModelArrayList;
    }

    public String getTitlre() {
        return Titlre;
    }

    public void setTitlre(String titlre) {
        Titlre = titlre;
    }

    public List<ProductModel> getProductModelArrayList() {
        return productModelArrayList;
    }

    public void setProductModelArrayList(List<ProductModel> productModelArrayList) {
        this.productModelArrayList = productModelArrayList;
    }
}
