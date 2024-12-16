package com.one.demo.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("vegetablesAndFruits")
    @Expose
    private List<VegetablesAndFruit> vegetablesAndFruits;
    @SerializedName("groceries")
    @Expose
    private List<Grocery> groceries;
    @SerializedName("SpecialDeals")
    @Expose
    private List<SpecialDeal> specialDeals;
    @SerializedName("bestSeller")
    @Expose
    private List<BestSeller> bestSeller;
    @SerializedName("freshVegetables")
    @Expose
    private List<FreshVegetable> freshVegetables;
    @SerializedName("freshFruits")
    @Expose
    private List<FreshFruit> freshFruits;
    @SerializedName("MoreToExplore")
    @Expose
    private List<Object> moreToExplore;

    public List<VegetablesAndFruit> getVegetablesAndFruits() {
        return vegetablesAndFruits;
    }

    public void setVegetablesAndFruits(List<VegetablesAndFruit> vegetablesAndFruits) {
        this.vegetablesAndFruits = vegetablesAndFruits;
    }

    public List<Grocery> getGroceries() {
        return groceries;
    }

    public void setGroceries(List<Grocery> groceries) {
        this.groceries = groceries;
    }

    public List<SpecialDeal> getSpecialDeals() {
        return specialDeals;
    }

    public void setSpecialDeals(List<SpecialDeal> specialDeals) {
        this.specialDeals = specialDeals;
    }

    public List<BestSeller> getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(List<BestSeller> bestSeller) {
        this.bestSeller = bestSeller;
    }

    public List<FreshVegetable> getFreshVegetables() {
        return freshVegetables;
    }

    public void setFreshVegetables(List<FreshVegetable> freshVegetables) {
        this.freshVegetables = freshVegetables;
    }

    public List<FreshFruit> getFreshFruits() {
        return freshFruits;
    }

    public void setFreshFruits(List<FreshFruit> freshFruits) {
        this.freshFruits = freshFruits;
    }

    public List<Object> getMoreToExplore() {
        return moreToExplore;
    }

    public void setMoreToExplore(List<Object> moreToExplore) {
        this.moreToExplore = moreToExplore;
    }

}
