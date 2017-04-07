package com.org.tijarah.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Excalibur Creations on 4/6/2017.
 */

public class Basket {

    public static List<Item> items;
    public static double total;


    public Basket() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return this.items;
    }

    public Item getItem(Item item) {
        if(items.contains(item)){
            for(Item i : items){
                if(i.getName().equals(item)){
                    return item;
                }
            }
        }else{
            return null;
        }

        return item;
    }

    public void addItems(Item item) {
        items.add(item);
    }

    public void removeItems(Item item) {
        items.remove(item);
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return items + " : " + total;
    }
}
