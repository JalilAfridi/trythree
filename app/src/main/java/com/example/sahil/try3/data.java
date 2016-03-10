package com.example.sahil.try3;

import java.util.ArrayList;

/**
 * Created by sahil on 1/19/2016.
 */
public class data {

    ArrayList<String> itemslist= new ArrayList<String>();
    ArrayList<String> itemsrates = new ArrayList<String>();

    data(){

        itemslist.add("carrot");
        itemslist.add("potato" );
        itemslist.add("Cucumber");
        itemslist.add("Banana");
        itemslist.add("etc");
        itemsrates.add("12");
        itemsrates.add("1");
        itemsrates.add("2");
        itemsrates.add("17");
        itemsrates.add("6");
    }

    public ArrayList<String> getItemsrates() {
        return itemsrates;
    }

    public ArrayList<String> getItemslist() {
        return itemslist;
    }
}
