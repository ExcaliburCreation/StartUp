package com.org.tijarah.structure;

/**
 * Created by Excalibur Creations on 3/31/2017.
 */

public class Category {

    private String Name;
    private String id;

    public Category(){}

    public Category (String Name,String id)
    {
        this.Name = Name;
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
