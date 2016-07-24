package com.example.sahil.try3;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Base64InputStream;
import android.util.Log;
import android.widget.Toast;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Created by sahil on 1/19/2016.
 */
public class data  {

    ArrayList<String> itemslist= new ArrayList<String>();
    ArrayList<String> itemsrates = new ArrayList<String>();
    ArrayList<String> ItemsIcon = new ArrayList<String>();
     DBHelper realdb ;

    data(){

        try {

        itemslist.add("carrot");
        itemslist.add("capsicum" );
        itemslist.add("Eggplant");
        itemslist.add("Pumpkin");
        itemslist.add("Onion");
        itemslist.add("Potato");
        itemslist.add("Tomato");
        itemslist.add("Laddy Finger");
        itemslist.add("Banana");




        itemsrates.add("12");
        itemsrates.add("1");
        itemsrates.add("2");
        itemsrates.add("17");
        itemsrates.add("6");
        itemsrates.add("34");
        itemsrates.add("67");
        itemsrates.add("3");
        itemsrates.add("14");





    }catch (Exception e ){
        e.printStackTrace();
    }


    }


    public void setupdb(Context con){




        realdb.insertItem("carrot","98","sasdagadfasdasd");
        realdb.insertItem("capsicum","32","sasdagadfasdasd");
        realdb.insertItem("Eggplant","78","sasdagadfasdasd");
        realdb.insertItem("Pumpkin","98","sasdagadfasdasd");
        realdb.insertItem("Eggplant","78","sasdagadfasdasd");
        realdb.insertItem("Pumpkin","98","sasdagadfasdasd");

        realdb.updateItems(0,"carrot","100","asdaflasflaskjdasljdlaskld");
        ArrayList temp =  realdb.getAllItems("price");
        if(temp.size()>1) {
            itemsrates.set(0, temp.get(0) + "");
            Log.i("test",temp.get(0)+"");
        }
        Toast.makeText(con, "loaded resource"+temp.get(0), Toast.LENGTH_SHORT).show();
    }

    public ArrayList<String> getItemsrates() {
        return itemsrates;
    }

    public ArrayList<String> getItemslist() {
        return itemslist;
    }

    public ArrayList<String> getItemsIcon() {
        return ItemsIcon;
    }
}
