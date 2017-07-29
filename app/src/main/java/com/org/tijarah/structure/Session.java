package com.org.tijarah.structure;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Excalibur Creations on 4/6/2017.
 */

public class Session implements Serializable{

    public static Basket basket;
    public static List<Item> items;

    public Session() {
        basket = new Basket();
        items = new ArrayList<Item>();
    }

    public Session(Basket basket) {
        this.basket = basket;
    }

    public static List<Item> getItems() {
        return items;
    }

    public static void setItems(List<Item> items) {
        Session.items = items;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @Nullable
    public static Item getItem(Item item) {
        if(items.contains(item)){
            for(Item i : items){
                if(i.getName().equals(item.getName())){
                    return i;
                }
            }
        }else{
            return null;
        }

        return item;
    }

    @Override
    public String toString() {
        return basket.toString();
    }
}
