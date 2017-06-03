package com.org.tijarah.structure;

import java.io.Serializable;

/**
 * Created by Excalibur Creations on 3/24/2017.
 */

public class Item implements Serializable {

    private String Unit;
    private String Price;
    private String Name;
    private String Description;
    private String imageUrl;
    private String quantity;
    private String count;
    private String category;
    private String isAdded;
    private int id;

    public Item() {

        this.Name = new String();
        this.imageUrl = new String();
        this.quantity = new String();
        this.Price = new String();
        this.count = new String("0");
        this.category = new String();
        this.isAdded = new String();
    }

    public Item(int id, String name, int quantity, double price, String category) {
        this.id = id;
        this.Name = name;
        this.quantity = String.valueOf(quantity);
        this.Price = String.valueOf(price);
        this.count = new String("0");
        this.category = category;

    }

    public Item(String name, String quantity, String price, String category) {
        this.Name = name;
        this.quantity = quantity;
        this.Price = price;
        this.category = category;
        this.count = new String("0");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 /*   public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }*/

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getIsAdded() {
        return isAdded;
    }

    public void setIsAdded(String isAdded) {
        this.isAdded = isAdded;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return Integer.parseInt(quantity);
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return Integer.parseInt(count);
    }

    public void setCount(String count) {
        this.count = count;
    }

    public double getPrice() {
        return Double.parseDouble(Price);
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAdded() {

        if (this.isAdded.equals("")) {
            return false;
        }
        return true;
    }

    public void setAdded(boolean added) {
        if (this.isAdded.equals("")) {
            isAdded = "";
        }
        isAdded = "true";
    }


    @Override
    public String toString() {
        return "Item{" +
                ", name='" + Name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", quantity=" + quantity +
                ", price=" + Price +
                ", count=" + count +
                ", category='" + category + '\'' +
                ", isAdded=" + isAdded +
                '}';
    }


}
