package com.org.tijarah.structure;

/**
 * Created by LENOVO on 6/14/2017.
 */

public class BasketItem {

    Item item;
    int quantity;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "BasketItem{" +
                "item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
