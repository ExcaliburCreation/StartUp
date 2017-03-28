package com.org.tijarah.structure;

/**
 * Created by Excalibur Creations on 3/24/2017.
 */

public class Item {

    private String name;
    private String imageUrl;
    private int quantity;
    private double price;
    private String category;
    private boolean isAdded;

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

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }

    @Override
    public String toString() {
        return name;
    }
}
