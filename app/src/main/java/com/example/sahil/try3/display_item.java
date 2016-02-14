package com.example.sahil.try3;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class display_item extends Activity {

    private ArrayAdapter<String> ListAdapter;
    private ListView detecteditemslist,detecteditemsRates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);

        detecteditemslist = (ListView)findViewById(R.id.detecteditemlist);
        detecteditemsRates = (ListView)findViewById(R.id.detecteditemsrates);

        //get instance of database
        single database = single.getInstance();
        ArrayList<String> itemsName = new ArrayList<String>();
        ArrayList<String> itemsRates = new ArrayList<String>();
        itemsName = database.datahub.getItemslist();
        itemsRates = database.datahub.getItemsrates();


        //displaying the list of items  on listview
        ListAdapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_multichoice, itemsName);
        detecteditemslist.setAdapter(ListAdapter);

        //displaying the list of items rates on listview
        ListAdapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_multichoice, itemsRates);
        detecteditemsRates.setAdapter(ListAdapter);





    }

}
