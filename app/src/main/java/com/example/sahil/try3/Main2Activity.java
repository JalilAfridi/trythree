package com.example.sahil.try3;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.widget.GridView;


public class Main2Activity extends Activity {
    GridView gv;
    ArrayList prgmName;
    public static String [] prgmNameList ={"Carrot","capsicum","Eggplant","Pumpkin","Onion","Potato","Tomato","Laddy Finger","Banana"};
    public static int [] prgmImages={R.drawable.images,R.drawable.images1,R.drawable.images2,R.drawable.images3,R.drawable.images4,R.drawable.images5,R.drawable.images6,R.drawable.images7,R.drawable.images8};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gv = (GridView)findViewById(R.id.gridView1);

        gv=(GridView) findViewById(R.id.gridView1);
        gv.setAdapter(new CustomAdapter(Main2Activity.this, prgmNameList , prgmImages));
    }

}
