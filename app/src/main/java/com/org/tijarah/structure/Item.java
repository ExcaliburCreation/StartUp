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
   // private String id;
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

    public Item(Item item){
        this.Name = item.getName();
        this.quantity = String.valueOf(item.getQuantity());
        this.Price = String.valueOf(item.getPrice());
        this.category = item.getCategory();
        this.count = String.valueOf(item.getCount());
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

    public String getQuantity() {
        return quantity;
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
                ", id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", quantity=" + quantity +
                ", price=" + Price +
                ", count=" + count +
                ", category='" + category + '\'' +
                ", isAdded=" + isAdded +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (!getUnit().equals(item.getUnit())) return false;
        if (getPrice() != (item.getPrice())) return false;
        if (getName() != null ? !getName().equals(item.getName()) : item.getName() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(item.getDescription()) : item.getDescription() != null)
            return false;
        if (getImageUrl() != null ? !getImageUrl().equals(item.getImageUrl()) : item.getImageUrl() != null)
            return false;
        if (getCount() != (item.getCount())) return false;
        if (getCategory() != null ? !getCategory().equals(item.getCategory()) : item.getCategory() != null)
            return false;
        return getIsAdded() != null ? getIsAdded().equals(item.getIsAdded()) : item.getIsAdded() == null;

    }

    @Override
    public int hashCode() {
        int result = getUnit().hashCode();
        result = 31 * result + Double.valueOf(getPrice()).hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getImageUrl() != null ? getImageUrl().hashCode() : 0);
        result = 31 * result + Integer.valueOf(getQuantity()).hashCode();
        result = 31 * result + Integer.valueOf(getCount()).hashCode();
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getIsAdded() != null ? getIsAdded().hashCode() : 0);
        return result;
    }
}
