package com.example.sahil.try3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainPage extends Activity {

    Button itemslistbutton , ImageproButton ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);



        itemslistbutton = (Button) findViewById(R.id.items_List);

        ImageproButton =(Button) findViewById(R.id.ImageproButton);



        ImageproButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainPage.this,ClassImagePro.class);
                startActivity(i);
            }
        });

        itemslistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainPage.this,Main2Activity.class);
                startActivity(i);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        single obj = single.getInstance();
        DBHelper realdb = new DBHelper(this);
        obj.datahub.realdb = realdb;
        Cursor cr = realdb.getData("name");

        //if(cr.isNull(0)) {
            obj.datahub.setupdb(this);

       // }
    }
}
