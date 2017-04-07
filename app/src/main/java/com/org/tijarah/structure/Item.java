package com.org.tijarah.structure;

/**
 * Created by Excalibur Creations on 3/24/2017.
 */

public class Item {

    private String name;
    private String imageUrl;
    private int quantity;
    private double price;
    private int count;
    private String category;
    private boolean isAdded;

    public Item(){
        this.name = new String();
        this.imageUrl = new String();
        this.quantity = 0;
        this.price = 0;
        this.count = 0;
        this.category = new String();
        this.isAdded = new Boolean(false);
    }

    public Item(String name, int quantity, double price, String category){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }

    @Override
    public String toString() {
        return name +" : "+ price ;
    }
}
