package com.example.sahil.try3;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class popup extends Activity implements  TextToSpeech.OnInitListener{

    Main2Activity obj = new Main2Activity();
    Button speakbtn;
     TextToSpeech tts;
    String rate="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        Intent i = getIntent();

        rate = i.getStringExtra("rate");
        String name = i.getStringExtra("item");
        Button pop = (Button)findViewById(R.id.pop_button);
        speakbtn = (Button) findViewById(R.id.speakerbutton);

        tts = new TextToSpeech(this, this);




        speakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });



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

    @Override
    public void onDestroy() {

        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(new Locale("hi"));

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakbtn.setEnabled(true);

            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void speak() {


        String text = rate +" रुपये";

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }









}
