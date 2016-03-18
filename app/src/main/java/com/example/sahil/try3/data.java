package com.example.sahil.try3;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sahil on 1/19/2016.
 */
public class data  {

    ArrayList<String> itemslist= new ArrayList<String>();
    ArrayList<String> itemsrates = new ArrayList<String>();

    data(){

        itemslist.add("carrot");
        itemslist.add("potato" );
        itemslist.add("Cucumber");
        itemslist.add("Banana");
        itemslist.add("Pumpkin");
        itemslist.add("Capsicum");
        itemslist.add("Eggplant");
        itemslist.add("Onion");
        itemslist.add("Potato");
        itemslist.add("Tomato");
        itemslist.add("Laddy finger");


        itemsrates.add("12");
        itemsrates.add("1");
        itemsrates.add("2");
        itemsrates.add("17");
        itemsrates.add("6");
        itemsrates.add("34");
        itemsrates.add("67");
        itemsrates.add("3");
        itemsrates.add("14");
        itemsrates.add("44");
        itemsrates.add("54");

    }

    public ArrayList<String> getItemsrates() {
        return itemsrates;
    }

    public ArrayList<String> getItemslist() {
        return itemslist;
    }

}
