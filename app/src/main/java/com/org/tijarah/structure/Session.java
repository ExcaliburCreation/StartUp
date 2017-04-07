package com.org.tijarah.structure;

/**
 * Created by Excalibur Creations on 4/6/2017.
 */

public class Session {

    public static Basket basket;

    public Session(){
        basket = new Basket();
    }
    public Session(Basket basket) {
        this.basket = basket;
    }


    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @Override
    public String toString() {
        return basket.toString();
    }
}
