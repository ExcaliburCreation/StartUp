package com.org.tijarah.structure;

/**
 * Created by Excalibur Creations on 09-May-17.
 */

public class ItemA {


   // private String id;
    private String Unit;
    private String Price;
    private String Name;
    private String Description;
    private String count;




    public ItemA(){

    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    @Override
    public String toString() {
        return "ItemA{" +
                "Unit='" + Unit + '\'' +
                ", Price='" + Price + '\'' +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
