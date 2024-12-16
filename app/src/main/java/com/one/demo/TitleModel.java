package com.one.demo;

import com.one.demo.model.VegetablesAndFruit;

import java.util.ArrayList;
import java.util.List;

public class TitleModel {

    String Titlre;
    List<VegetablesAndFruit> productModelArrayList;

    public TitleModel(String titlre, List<VegetablesAndFruit> productModelArrayList) {
        Titlre = titlre;
        this.productModelArrayList = productModelArrayList;
    }

    public String getTitlre() {
        return Titlre;
    }

    public void setTitlre(String titlre) {
        Titlre = titlre;
    }

    public List<VegetablesAndFruit> getProductModelArrayList() {
        return productModelArrayList;
    }

    public void setProductModelArrayList(List<VegetablesAndFruit> productModelArrayList) {
        this.productModelArrayList = productModelArrayList;
    }
}
