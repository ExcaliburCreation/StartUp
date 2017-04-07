package com.org.tijarah.structure;

/**
 * Created by Excalibur Creations on 3/31/2017.
 */

public class Category {

    private String Name;

    public Category(){}

    public Category (String Name)
    {
        this.Name = Name;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
