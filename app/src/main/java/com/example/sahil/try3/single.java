package com.example.sahil.try3;

/**
 * Created by sahil on 1/19/2016.
 */
public class single {
    private static single ourInstance = new single();

   data datahub = new data();


    public static single getInstance() {
        return ourInstance;
    }

    private single() {
    }
}
