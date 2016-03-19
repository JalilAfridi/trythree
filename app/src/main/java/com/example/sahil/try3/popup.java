package com.example.sahil.try3;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class popup extends Activity {

    Main2Activity obj = new Main2Activity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        Intent i = getIntent();
        String rate  = i.getStringExtra("rate");
        String name = i.getStringExtra("item");
        Button pop = (Button)findViewById(R.id.pop_button);

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        ImageView img = (ImageView) findViewById(R.id.popup_image);
        TextView t1 = (TextView)findViewById(R.id.popup_textView);

        for(int x=0;x<obj.prgmNameList.length;x++){
            if(obj.prgmNameList[x].equalsIgnoreCase(name)){

                img.setImageResource(obj.prgmImages[x]);
                t1.setText(" Name      : "+name+"\n Cost        : "+rate+"\n Currency :"+" Rupees ");
                break;
            }
        }


    }

}
