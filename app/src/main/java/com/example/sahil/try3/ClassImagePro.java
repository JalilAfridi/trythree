package com.example.sahil.try3;


import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ClassImagePro extends Activity {
    private static final int TAKE_PICTURE_REQUEST_B = 100;
    Spinner spinner ;
    ArrayList<String> items ;
    int position1 = 0;


    Button bx,bxn,by,byn,A,B,display;
    int[] khan = new int[15];
    Mat orignal;
    Mat filter;
    Mat graydst;
    Mat copy ;
    Mat gray ;
    ImageView i,i2;
    int dx=0,dy=0,dept=1,cntr=0,found=-1;
    double red=0,green=0,blue=0,redg=0,greeng=0,blueg=0;
    EditText  redd,greenn,bluee,reddg,greenng,blueeg;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView;
    String scolor = "noCOlor";
    Button camera;
    single dbaccess = single.getInstance();


    static {

        System.loadLibrary("opencv_java3");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (Button)findViewById(R.id.display);
        bx = (Button)findViewById(R.id.x);
        bxn = (Button)findViewById(R.id.Xn);
        by = (Button)findViewById(R.id.y);
        byn = (Button)findViewById(R.id.Yn);
        A = (Button)findViewById(R.id.updates);
        B = (Button)findViewById(R.id.b);
        i2 = (ImageView)findViewById(R.id.image);
        redd =(EditText)findViewById(R.id.red);
        greenn = (EditText)findViewById(R.id.green);
        bluee= (EditText)findViewById(R.id.blue);
        reddg =(EditText)findViewById(R.id.redg);
        greenng = (EditText)findViewById(R.id.greeng);
        blueeg= (EditText)findViewById(R.id.blueg);
        spinner = (Spinner)findViewById(R.id.spinner);

        redd.setVisibility(View.INVISIBLE);
        greenn.setVisibility(View.INVISIBLE);
        bluee.setVisibility(View.INVISIBLE);
        reddg.setVisibility(View.INVISIBLE);
        greenng.setVisibility(View.INVISIBLE);
        blueeg.setVisibility(View.INVISIBLE);



        ArrayAdapter<String> adp1=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,dbaccess.datahub.getItemslist());
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp1);
        items = dbaccess.datahub.getItemslist();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                              @Override
                                              public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                                                  position1 = position;


                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {

                                              }

                                          });
            dept = R.drawable.ban4;
        int asay =0;
        try {
            copy= Utils.loadResource(ClassImagePro.this,dept, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);


        } catch (IOException e) {
            e.printStackTrace();
        }
        camera = (Button) findViewById(R.id.camer);
        camera.setOnClickListener(
                new View.OnClickListener(){


                    @Override
                    public void onClick(View v) {
                       // Intent camerintent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        Intent camerintent =  new Intent(ClassImagePro.this,CameraActivity.class);
                        startActivityForResult(camerintent,100);
                    }
                }

        );
        bx.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dx++;
                        dept++;

                    }
                }
        );
        bxn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dx--;
                        dept--;

                    }
                }
        );
        by.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dy++;

                    }
                }
        );
        byn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {

                      //    copy = Utils.loadResource(ClassImagePro.this, R.drawable.ban2, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);

                            dy--;
                        }catch (Exception e){

                        }
                    }
                }
        );
        A.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        final connection con = new connection();
                        con.startconnection();
                        final TextView t1 = (TextView)findViewById(R.id.rawdata);

                        new CountDownTimer(3000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            public void onFinish() {
                                // When timer is finished
                                t1.setText(con.string);
                            }
                        }.start();


                       // String ar[] = {" R.drawable.ban", " R.drawable.ban", " R.drawable.ban2"};

                      /*    try {   if (cntr == 0) {

                                Toast.makeText(ClassImagePro.this, ""+dept, Toast.LENGTH_SHORT).show();

                                copy = Utils.loadResource(ClassImagePro.this,dept, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
                                cntr=0;
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

*/
                    }
                }

        );

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ClassImagePro.this,display_item.class);
                startActivity(i);
            }
        });

        B.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int banFlag=0;
                        int carrotFlag=0;
                       if( items.get(position1).equalsIgnoreCase("banana")){

                           banFlag = banana1();
                       }else  if( items.get(position1).equalsIgnoreCase("carrot")){


                           carrotFlag = carrott();
                       }else if (items.get(position1).equalsIgnoreCase("potato")){

                           int potatoFlag =  potato();

                        }else {
                           Toast.makeText(ClassImagePro.this, "select an item from the list", Toast.LENGTH_SHORT).show();
                       }



                    int pumpkinFlag = 0;//pumpkin();
                     int cucumberFlag =0; //cucumber();


  //                      TextView t1 = (TextView)findViewById(R.id.rawdata);
//                        t1.setText(" ban: " + banFlag+ " carrot: "+carrotFlag+" pumpkin "+pumpkinFlag+" cucumber "+cucumberFlag );

                    //  Intent i = new Intent(ClassImagePro.this,display_item.class);

                     //  startActivity(i);
                     //

                        //haarcascad();



                    }



                }

        );




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PICTURE_REQUEST_B) {
            Bitmap mCameraBitmap = null;
            if (resultCode == RESULT_OK) {
                // Recycle the previous bitmap.
                if (mCameraBitmap != null) {
                    mCameraBitmap.recycle();
                    mCameraBitmap = null;
                }
                Bundle extras = data.getExtras();
                mCameraBitmap = (Bitmap) extras.get("data");
                byte[] cameraData = extras.getByteArray(CameraActivity.EXTRA_CAMERA_DATA);
                if (cameraData != null) {
                    mCameraBitmap = BitmapFactory.decodeByteArray(cameraData, 0, cameraData.length);
                    Utils.bitmapToMat(mCameraBitmap, copy);

                    Imgproc.cvtColor(copy,copy,Imgproc.COLOR_RGB2BGR);
                    i2.setImageBitmap(mCameraBitmap);

                }
            } else {
                mCameraBitmap = null;

            }
        }
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);





        Toast.makeText(ClassImagePro.this, "  load: ", Toast.LENGTH_SHORT).show();
              Bitmap v = (Bitmap)  data.getExtras().get("data");
                Utils.bitmapToMat(v, copy);

             Imgproc.cvtColor(copy,copy,Imgproc.COLOR_RGB2BGR);
                i2.setImageBitmap(v);




    }
*/

             int banana1(){

              try{
                    Mat fulhsv = copy.clone();

                    Imgproc.cvtColor(fulhsv, fulhsv, Imgproc.COLOR_BGR2HSV_FULL);


                    Scalar min  = new Scalar(10,80,150);
                    Scalar max  = new Scalar(40,250,256);

                    Core.inRange(fulhsv, min, max, fulhsv);


                   Imgproc.GaussianBlur(fulhsv, fulhsv, new Size(5, 5), 5);
                    Imgproc.Canny(fulhsv, fulhsv, 1, 400);
                    Imgproc.dilate(fulhsv,fulhsv,new Mat());
                   // Imgproc.Canny(fulhsv, fulhsv, 20, 50);

                    orignal = fulhsv.clone();
                    List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
                    List<MatOfPoint> filteredcontours = new ArrayList<MatOfPoint>();

                    Imgproc.findContours(fulhsv, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

                    MatOfPoint firstcountour=null;

                    for(int i=0;i<contours.size();i++){

                        if(Imgproc.contourArea(contours.get(i))>1000){
                            filteredcontours.add(contours.get(i));
                        }

                    }


            double confidancelevel = Imgproc.matchShapes(filteredcontours.get(dy),filteredcontours.get(dx) , Imgproc.CV_CONTOURS_MATCH_I3, 1);


            TextView rawdata = (TextView) findViewById(R.id.rawdata);

            rawdata.setText("confidance: " + confidancelevel+"y: "+dy);




            try {
              firstcountour = contours.get(0);
            }catch (Exception e){

                Toast.makeText(ClassImagePro.this, "Please take picture from different Angle", Toast.LENGTH_LONG).show();
            }
            double areaofcontour = Imgproc.contourArea(firstcountour);

            Rect boundingrect=Imgproc.boundingRect(firstcountour);;
            double wedth ;
            double hieght;
            double ratio = 0 ;
            int counter =0;
            double db=0.0;
            //checking for banana only
            for(int i=0;i<contours.size();i++){

                areaofcontour = Imgproc.contourArea(contours.get(i));
                wedth =   boundingrect.width;
                hieght = boundingrect.height;


                if(wedth>hieght){

                    ratio=wedth/hieght;
                }else{
                    ratio=hieght/wedth;

                }

                if(areaofcontour>1000&&ratio>1.3){

                    firstcountour = contours.get(i);
                    boundingrect = Imgproc.boundingRect(firstcountour);

                    MatOfPoint2f m2 = new MatOfPoint2f(firstcountour.toArray());


                    db = Imgproc.arcLength(m2, true);
                    scolor="yellow";
                    Toast.makeText(ClassImagePro.this, "yellow done", Toast.LENGTH_SHORT).show();
                    break;
                }


            }

            finaltest(ratio,areaofcontour);



            try {
                ArrayList<MatOfPoint> largest_contours = new ArrayList<MatOfPoint>();
            largest_contours.add(filteredcontours.get(dy));
              largest_contours.add(filteredcontours.get(dx));

              Imgproc.drawContours(orignal, largest_contours, -1, new Scalar(255, 255, 0), 1);




        }catch(Exception e){

        }

            Bitmap img2 = Bitmap.createBitmap(orignal.cols(), orignal.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(orignal, img2);
          i2.setImageBitmap(img2);
            Toast.makeText(ClassImagePro.this, " image displayed ", Toast.LENGTH_SHORT).show();



        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(ClassImagePro.this, "Excption", Toast.LENGTH_SHORT).show();
        }


        return 0;
    }

    int carrott(){

        try{
            Mat fulhsv = copy.clone();
            Imgproc.cvtColor(copy, fulhsv, Imgproc.COLOR_BGR2HSV_FULL);


            Scalar min  = new Scalar(2,120,200);
            Scalar max  = new Scalar(10,170,256);


            Core.inRange(fulhsv, min, max, fulhsv);
            orignal = fulhsv.clone();
            Imgproc.Canny(fulhsv, fulhsv, 200, 50);
            Imgproc.GaussianBlur(fulhsv, fulhsv, new Size(5, 5), 5);
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(fulhsv, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            MatOfPoint firstcountour=null;
            try {
                firstcountour = contours.get(0);
            }catch (Exception e){

                Toast.makeText(ClassImagePro.this, "Please take picture from different Angle", Toast.LENGTH_LONG).show();
            }
            double areaofcontour = Imgproc.contourArea(firstcountour);

            Rect boundingrect=Imgproc.boundingRect(firstcountour);;
            double wedth ;
            double hieght;
            double ratio = 0 ;
            int counter =0;
            double db=0.0;
            //checking for banana only
            for(int i=0;i<contours.size();i++){

                areaofcontour = Imgproc.contourArea(contours.get(i));
                wedth =   boundingrect.width;
                hieght = boundingrect.height;


                if(wedth>hieght){

                    ratio=wedth/hieght;
                }else{
                    ratio=hieght/wedth;

                }

                if(areaofcontour>1000&&ratio>1.3){

                    firstcountour = contours.get(i);
                    boundingrect = Imgproc.boundingRect(firstcountour);

                    MatOfPoint2f m2 = new MatOfPoint2f(firstcountour.toArray());


                    db = Imgproc.arcLength(m2, true);
                    scolor="red";
                    Toast.makeText(ClassImagePro.this, "red done", Toast.LENGTH_SHORT).show();
                    break;
                }


            }

            finaltest(ratio,areaofcontour);
            try {
                ArrayList<MatOfPoint> largest_contours = new ArrayList<MatOfPoint>();
                largest_contours.add(firstcountour);


                Imgproc.cvtColor(gray, gray, Imgproc.COLOR_BayerBG2RGB);
                Imgproc.drawContours(orignal, largest_contours, -1, new Scalar(255, 255, 0), 1);


            }catch(Exception e){

            }

            Bitmap img2 = Bitmap.createBitmap(orignal.cols(), orignal.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(orignal, img2);
            i2.setImageBitmap(img2);
            Toast.makeText(ClassImagePro.this, " image displayed ", Toast.LENGTH_SHORT).show();



        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(ClassImagePro.this, "Excption", Toast.LENGTH_SHORT).show();
        }


        return 0;
    }
    int bananaold(){
        TextView t1 = (TextView)findViewById(R.id.rawdata);
        t1.setText(" y: " + dy);

        try{
  //  gray = Utils.loadResource(ClassImagePro.this, R.drawable.ban8, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);

    gray = copy.clone();
    Mat orignal = gray.clone();


            // Imgproc.cvtColor(gray,gray,Imgproc.COLOR_BGR2RGB);
            ///  double[] ar = colored.get(1,1);
            ///  double[] black = {0,0,0};
            // colored.put(1,1,black);
                          /*  red =Double.parseDouble(redd.getText().toString());
                            green =Double.parseDouble(greenn.getText().toString());
                            blue =Double.parseDouble(bluee.getText().toString());
                            //segment using color
                          for(int i=1;i<colored.rows();i++){
                                for(int y=1;y<colored.cols();y++){
                                  if(colored.get(i,y)[0]>red&&colored.get(i,y)[1]>blue&&colored.get(i,y)[2]<green){
                                        continue;
                                    }
                                    else {
                                        double[] black = {0,0,0};
                                        colored.put(i,y,black);
                                    }

                                }

                            }
                            */

            //  Imgproc.pyrMeanShiftFiltering(gray,gray,dy,dept);



                Imgproc.cvtColor(gray, gray, Imgproc.COLOR_BGR2GRAY);

                //convert the image to black and white does (8 bit)
                Imgproc.Canny(gray, gray, 200, 55);

                //apply gaussian blur to smoothen lines of dots
                Imgproc.GaussianBlur(gray, gray, new Size(5, 5), 5);
                List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

                Imgproc.findContours(gray, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);


                MatOfPoint largCon = contours.get(0);
                Double areaofcontour = Imgproc.contourArea(contours.get(0));
                Boolean convex = true;

                int px = 0;
                int py = 0;


            List<Point>  ls = contours.get(0).toList();

            Point p = ls.get(0);
            px = (int)p.x ;
            py = (int)p.y;




            Mat fulhsv = new Mat();
            Imgproc.cvtColor(orignal,orignal,Imgproc.COLOR_BGR2RGB);
            Imgproc.cvtColor(orignal, fulhsv, Imgproc.COLOR_RGB2HSV);


            double[] hsv = fulhsv.get(1, 11);





          //  red =Double.parseDouble(redd.getText().toString());
         //   green =Double.parseDouble(greenn.getText().toString());
         //   blue =Double.parseDouble(bluee.getText().toString());
          //  redg =Double.parseDouble(reddg.getText().toString());
          //  greeng =Double.parseDouble(greenng.getText().toString());
          //  blueg =Double.parseDouble(blueeg.getText().toString());

           Scalar min  = new Scalar(10,80,200);
           Scalar max  = new Scalar(40,200,256);


            Core.inRange(fulhsv, min, max, fulhsv);




           /*
            //segment using color
            for(int i=1;i<fulhsv.rows();i++){
                for(int y=1;y<fulhsv.cols();y++){
                    if(fulhsv.get(i,y)[0]>10&&fulhsv.get(i,y)[0]<35&&fulhsv.get(i,y)[1]>50&&fulhsv.get(i,y)[1]<200&&fulhsv.get(i,y)[2]>200&&fulhsv.get(i,y)[2]<256){
                        continue;
                    }
                    else {
                        double[] black = {0,0,0};
                        fulhsv.put(i,y,black);
                    }

                }

            }
*/
            Imgproc.Canny(fulhsv, orignal, 200, 50);
            //apply gaussian blur to smoothen lines of dots
            Imgproc.GaussianBlur(orignal, orignal, new Size(5, 5), 5);
            List<MatOfPoint> contours1 = new ArrayList<MatOfPoint>();
            Imgproc.findContours(orignal, contours1, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            MatOfPoint largCon1=contours1.get(0);
            Double areaofcontour1 = Imgproc.contourArea(contours1.get(0));

            Boolean convex1 =  true;
            orignal = fulhsv;
            Rect boundingrect=Imgproc.boundingRect(largCon1);;
            double wedth ;
            double hieght;
            double ratio = 0 ;
            int counter =0;
            double db=0.0;
            //checking for banana only
            for(int i=0;i<contours1.size();i++){

                areaofcontour1 = Imgproc.contourArea(contours1.get(i));
                wedth =   boundingrect.width;
                hieght= boundingrect.height;


                if(wedth>hieght){

                    ratio=wedth/hieght;
                }else{
                    ratio=hieght/wedth;

                }

                if(areaofcontour1>1000&&ratio>1.3){

                    largCon1 = contours1.get(i);
                    boundingrect = Imgproc.boundingRect(largCon1);

                    MatOfPoint2f m2 = new MatOfPoint2f(largCon1.toArray());


                    db = Imgproc.arcLength(m2, true);
                    scolor="yellow";
                    Toast.makeText(ClassImagePro.this, "yellow done", Toast.LENGTH_SHORT).show();
                    break;
                }


        }

            //find largest contour
          //  for(int i =)



            //  Toast.makeText(ClassImagePro.this, "h: "+hsv[0]+" s: "+hsv[1]+ " v: "+hsv[2], Toast.LENGTH_LONG).show();


            if(ratio<1.3&&areaofcontour1>1000){
                Acircle circlobject = new Acircle();
                if(scolor.equalsIgnoreCase("red")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("green")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("white")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("brown")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("whitegreen")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ClassImagePro.this, " something else ", Toast.LENGTH_SHORT).show();
                }


            }else if(ratio>1.3&&areaofcontour1>1000){

                Blong longObject = new Blong();
                if(scolor.equalsIgnoreCase("yellow")){
                    String vegname= longObject.yellow();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                    Bitmap img2 = Bitmap.createBitmap(orignal.cols(), orignal.rows(), Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(orignal, img2);
                    i2.setImageBitmap(img2);

                    return 1;
                }else if(scolor.equalsIgnoreCase("red")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("lightgreen")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("darkgreen")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("purple")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ClassImagePro.this, " something else! ", Toast.LENGTH_SHORT).show();
                }


            }

            // Mat matat30 = contours.get(30).t();


            //   Mat m = contours.get(30).t();
            //finding minimum & maximum xpoint and at a give y point
            //double min=point[0].x,max=point[0].x;



           // t1.setText(t1.getText().toString() + " area: " + areaofcontour1 + " data1: " + hsv[0] + " data2: " + hsv[1] + " data3: " + hsv[2]+" db: "+db);
            ArrayList<MatOfPoint> largest_contours = new ArrayList<MatOfPoint>();
            largest_contours.add(largCon1);


            Imgproc.cvtColor(gray, gray, Imgproc.COLOR_BayerBG2RGB);
            Imgproc.drawContours(orignal, largest_contours, -1, new Scalar(255, 255, 0), 1);


            Mat larg = gray;

            Bitmap img2 = Bitmap.createBitmap(orignal.cols(), orignal.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(orignal, img2);
            i2.setImageBitmap(img2);
            Toast.makeText(ClassImagePro.this, " image displayed ", Toast.LENGTH_SHORT).show();


        }catch(Exception e){

            Toast.makeText(ClassImagePro.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return -1;
    }
    int carrotold(){
        TextView t1 = (TextView)findViewById(R.id.rawdata);
        t1.setText(" y: " + dy);

        try{
            //  gray = Utils.loadResource(ClassImagePro.this, R.drawable.ban8, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);

            gray = copy.clone();
            Mat orignal = gray.clone();



            // Imgproc.cvtColor(gray,gray,Imgproc.COLOR_BGR2RGB);
            ///  double[] ar = colored.get(1,1);
            ///  double[] black = {0,0,0};
            // colored.put(1,1,black);
                          /*  red =Double.parseDouble(redd.getText().toString());
                            green =Double.parseDouble(greenn.getText().toString());
                            blue =Double.parseDouble(bluee.getText().toString());
                            //segment using color
                          for(int i=1;i<colored.rows();i++){
                                for(int y=1;y<colored.cols();y++){
                                  if(colored.get(i,y)[0]>red&&colored.get(i,y)[1]>blue&&colored.get(i,y)[2]<green){
                                        continue;
                                    }
                                    else {
                                        double[] black = {0,0,0};
                                        colored.put(i,y,black);
                                    }

                                }

                            }
                            */

            //  Imgproc.pyrMeanShiftFiltering(gray,gray,dy,dept);



            Imgproc.cvtColor(gray, gray, Imgproc.COLOR_BGR2GRAY);

            //convert the image to black and white does (8 bit)
            Imgproc.Canny(gray, gray, 200, 50);

            //apply gaussian blur to smoothen lines of dots
            Imgproc.GaussianBlur(gray, gray, new Size(5, 5), 5);
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

            Imgproc.findContours(gray, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);


            MatOfPoint largCon = contours.get(0);
            Double areaofcontour = Imgproc.contourArea(contours.get(0));
            Boolean convex = true;

            int px = 0;
            int py = 0;


            List<Point>  ls = contours.get(0).toList();

            Point p = ls.get(0);
            px = (int)p.x ;
            py = (int)p.y;




            Mat fulhsv = orignal.clone();
            Imgproc.cvtColor(orignal,orignal,Imgproc.COLOR_BGR2RGB);
            Imgproc.cvtColor(orignal, fulhsv, Imgproc.COLOR_RGB2HSV);


            double[] hsv = fulhsv.get(1, 11);





         //    red =Double.parseDouble(redd.getText().toString());
           //   green =Double.parseDouble(greenn.getText().toString());
             //blue =Double.parseDouble(bluee.getText().toString());
           //  redg =Double.parseDouble(reddg.getText().toString());
           // greeng =Double.parseDouble(greenng.getText().toString());
             //blueg =Double.parseDouble(blueeg.getText().toString());

            Scalar min  = new Scalar(2,120,200);
            Scalar max  = new Scalar(10,170,256);


            Core.inRange(fulhsv, min, max, fulhsv);




           /*
            //segment using color
            for(int i=1;i<fulhsv.rows();i++){
                for(int y=1;y<fulhsv.cols();y++){
                    if(fulhsv.get(i,y)[0]>10&&fulhsv.get(i,y)[0]<35&&fulhsv.get(i,y)[1]>50&&fulhsv.get(i,y)[1]<200&&fulhsv.get(i,y)[2]>200&&fulhsv.get(i,y)[2]<256){
                        continue;
                    }
                    else {
                        double[] black = {0,0,0};
                        fulhsv.put(i,y,black);
                    }

                }

            }
*/
            Imgproc.Canny(fulhsv, orignal, 200, 50);
            //apply gaussian blur to smoothen lines of dots
            Imgproc.GaussianBlur(orignal, orignal, new Size(5, 5), 5);
            List<MatOfPoint> contours1 = new ArrayList<MatOfPoint>();
            Imgproc.findContours(orignal, contours1, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            MatOfPoint largCon1=contours1.get(0);
            Double areaofcontour1 = Imgproc.contourArea(contours1.get(0));

            Boolean convex1 =  true;
            orignal = fulhsv;
            Rect boundingrect=Imgproc.boundingRect(largCon1);;
            double wedth ;
            double hieght;
            double ratio = 0 ;
            int counter =0;
            double db=0.0;
            //checking for carrot only
            for(int i=0;i<contours1.size();i++){

                areaofcontour1 = Imgproc.contourArea(contours1.get(i));
                wedth =   boundingrect.width;
                hieght= boundingrect.height;


                if(wedth>hieght){

                    ratio=wedth/hieght;
                }else{
                    ratio=hieght/wedth;

                }

                if(areaofcontour1>1000&&ratio>1.3){

                    largCon1 = contours1.get(i);
                    boundingrect = Imgproc.boundingRect(largCon1);

                    MatOfPoint2f m2 = new MatOfPoint2f(largCon1.toArray());


                    db = Imgproc.arcLength(m2, true);
                    scolor="red";
                    Toast.makeText(ClassImagePro.this, "red done", Toast.LENGTH_SHORT).show();
                    break;
                }


            }
            Toast.makeText(ClassImagePro.this, "ratio ", Toast.LENGTH_SHORT).show();
            //find largest contour
            //  for(int i =)



            //  Toast.makeText(ClassImagePro.this, "h: "+hsv[0]+" s: "+hsv[1]+ " v: "+hsv[2], Toast.LENGTH_LONG).show();


            if(ratio<1.3&&areaofcontour1>1000){
                Acircle circlobject = new Acircle();
                if(scolor.equalsIgnoreCase("red")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("green")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("white")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("brown")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("whitegreen")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ClassImagePro.this, " something else ", Toast.LENGTH_SHORT).show();
                }


            }else if(ratio>1.3&&areaofcontour1>1000){

                Blong longObject = new Blong();
                if(scolor.equalsIgnoreCase("yellow")){
                    String vegname= longObject.yellow();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("red")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                    Bitmap img2 = Bitmap.createBitmap(fulhsv.cols(), fulhsv.rows(), Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(fulhsv, img2);
                    i2.setImageBitmap(img2);

                    return 1;
                }else if(scolor.equalsIgnoreCase("lightgreen")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("darkgreen")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("purple")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ClassImagePro.this, " something else! ", Toast.LENGTH_SHORT).show();
                }


            }

            // Mat matat30 = contours.get(30).t();


            //   Mat m = contours.get(30).t();
            //finding minimum & maximum xpoint and at a give y point
            //double min=point[0].x,max=point[0].x;



            t1.setText(t1.getText().toString() + " area: " + areaofcontour1 + " data1: " + hsv[0] + " data2: " + hsv[1] + " data3: " + hsv[2]+" db: "+db);
            ArrayList<MatOfPoint> largest_contours = new ArrayList<MatOfPoint>();
            largest_contours.add(largCon1);


            Imgproc.cvtColor(orignal, orignal, Imgproc.COLOR_BayerBG2RGB);
            Imgproc.drawContours(orignal, largest_contours, -1, new Scalar(255, 255, 0), 1);


            Mat larg = gray;

            Bitmap img2 = Bitmap.createBitmap(orignal.cols(), orignal.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(orignal, img2);
            i2.setImageBitmap(img2);


        }catch(Exception e){

            Toast.makeText(ClassImagePro.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return -1;
    }
    int carrot1old(){

        TextView t1 = (TextView)findViewById(R.id.rawdata);
        t1.setText(" y: " + dy );
        try {

            //gray= Utils.loadResource(ClassImagePro.this, R.drawable.pveg, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
            gray = copy.clone();
            Mat orignal = gray.clone();

            // Imgproc.cvtColor(gray,gray,Imgproc.COLOR_BGR2RGB);
            ///  double[] ar = colored.get(1,1);
            ///  double[] black = {0,0,0};
            // colored.put(1,1,black);
                          /*  red =Double.parseDouble(redd.getText().toString());
                            green =Double.parseDouble(greenn.getText().toString());
                            blue =Double.parseDouble(bluee.getText().toString());
                            //segment using color
                          for(int i=1;i<colored.rows();i++){
                                for(int y=1;y<colored.cols();y++){
                                  if(colored.get(i,y)[0]>red&&colored.get(i,y)[1]>blue&&colored.get(i,y)[2]<green){
                                        continue;
                                    }
                                    else {
                                        double[] black = {0,0,0};
                                        colored.put(i,y,black);
                                    }

                                }

                            }
                            */

            //  Imgproc.pyrMeanShiftFiltering(gray,gray,dy,dept);



            Imgproc.cvtColor(gray, gray, Imgproc.COLOR_BGR2GRAY);

            //convert the image to black and white does (8 bit)
            Imgproc.Canny(gray, gray, 200, 50);

            //apply gaussian blur to smoothen lines of dots
            Imgproc.GaussianBlur(gray, gray, new Size(5, 5), 5);
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(gray, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            MatOfPoint largCon = contours.get(0);
            Double areaofcontour = Imgproc.contourArea(contours.get(0)) ;
            Boolean convex =  true;


            MatOfPoint2f m2 = new MatOfPoint2f(largCon.toArray());
            double  db = Imgproc.arcLength(m2, true);

            int px=0;int py=0;

               Mat m = contours.get(0).t();
                double h = m.height();
                double w = m.width();



            Mat fulhsv =new Mat();
            Imgproc.cvtColor(orignal, orignal, Imgproc.COLOR_BGR2RGB);
            Imgproc.cvtColor(orignal, fulhsv, Imgproc.COLOR_RGB2HSV);

            double[] hsv = fulhsv.get(py,px);


            Scalar min  = new Scalar(2,120,210);
            Scalar max  = new Scalar(10,170,256);


           Core.inRange(fulhsv, min, max, fulhsv);






/*
            //segment using color
            for(int i=1;i<fulhsv.rows();i++){
                for(int y=1;y<fulhsv.cols();y++){
                    if(fulhsv.get(i,y)[0]>2&&fulhsv.get(i,y)[0]<10&&fulhsv.get(i,y)[1]>120&&fulhsv.get(i,y)[1]<170&&fulhsv.get(i,y)[2]>210&&fulhsv.get(i,y)[2]<250){
                        continue;
                    }
                    else {
                        double[] black = {0,0,0};
                        fulhsv.put(i,y,black);
                    }

                }

            }
*/


            Imgproc.Canny(fulhsv, orignal, 200, 50);
            //apply gaussian blur to smoothen lines of dots
            Imgproc.GaussianBlur(orignal, orignal, new Size(5, 5), 5);
            List<MatOfPoint> contours1 = new ArrayList<MatOfPoint>();
            Imgproc.findContours(orignal, contours1, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            MatOfPoint largCon1 = null;
            Double areaofcontour1 = 0.0;


            largCon1 = contours1.get(0);

                areaofcontour1 = Imgproc.contourArea(contours1.get(0));

            Boolean convex1 =  true;

            Rect boundingrect=Imgproc.boundingRect(largCon1);;


           // checking for gajar only
            for(int i=0;i<contours1.size();i++){

                areaofcontour1 = Imgproc.contourArea(contours1.get(i));
                if(areaofcontour1>1000){

                    largCon1 = contours1.get(i);
                    boundingrect = Imgproc.boundingRect(largCon1);


                    scolor="red";
                    Toast.makeText(ClassImagePro.this, "red done", Toast.LENGTH_SHORT).show();
                  break;
             }



          }

            //  Toast.makeText(ClassImagePro.this, "h: "+hsv[0]+" s: "+hsv[1]+ " v: "+hsv[2], Toast.LENGTH_LONG).show();

            double wedth =   boundingrect.width;
            double hieght= boundingrect.height;

            double ratio ;
            if(wedth>hieght){
                ratio=wedth/hieght;
            }else{
                ratio=hieght/wedth;
            }

            if(ratio<1.25&&areaofcontour1>1000){
                Acircle circlobject = new Acircle();
                if(scolor.equalsIgnoreCase("red")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();


                }else if(scolor.equalsIgnoreCase("green")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("white")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("brown")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("whitegreen")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ClassImagePro.this, " something else ", Toast.LENGTH_SHORT).show();
                }


            }else if(ratio>1.3&&areaofcontour1>1000){

                Blong longObject = new Blong();
                if(scolor.equalsIgnoreCase("yellow")){
                    String vegname= longObject.yellow();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();
                }if(scolor.equalsIgnoreCase("red")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                    Bitmap img2 = Bitmap.createBitmap(fulhsv.cols(), fulhsv.rows(), Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(fulhsv, img2);
                    i2.setImageBitmap(img2);
                    return 1;

                }else if(scolor.equalsIgnoreCase("lightgreen")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("darkgreen")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("purple")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ClassImagePro.this, " something else ", Toast.LENGTH_SHORT).show();
                }


            }

            // Mat matat30 = contours.get(30).t();


            //   Mat m = contours.get(30).t();
            //finding minimum & maximum xpoint and at a give y point
            //double min=point[0].x,max=point[0].x;



            t1.setText(t1.getText().toString() + " area: " + areaofcontour1 +" db: "+db+" h: "+h+ " w: "+w );
            ArrayList<MatOfPoint> largest_contours = new ArrayList<MatOfPoint>();
            largest_contours.add(largCon1);


            Imgproc.cvtColor(gray, gray, Imgproc.COLOR_BayerBG2RGB);
            Imgproc.drawContours(fulhsv, largest_contours, -1, new Scalar(255,255,0), 1);


            Mat larg = gray;

            Bitmap img2 = Bitmap.createBitmap(fulhsv.cols(), fulhsv.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(fulhsv, img2);
            i2.setImageBitmap(img2);

        }catch(Exception e){

            Toast.makeText(ClassImagePro.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return -1;
    }
    int pumpkin(){

        TextView t1 = (TextView)findViewById(R.id.rawdata);
        t1.setText(" y: " + dy );
        try {

            //gray= Utils.loadResource(ClassImagePro.this, R.drawable.pveg, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
            gray = copy.clone();
            Mat orignal = gray.clone();

            // Imgproc.cvtColor(gray,gray,Imgproc.COLOR_BGR2RGB);
            ///  double[] ar = colored.get(1,1);
            ///  double[] black = {0,0,0};
            // colored.put(1,1,black);
                          /*  red =Double.parseDouble(redd.getText().toString());
                            green =Double.parseDouble(greenn.getText().toString());
                            blue =Double.parseDouble(bluee.getText().toString());
                            //segment using color
                          for(int i=1;i<colored.rows();i++){
                                for(int y=1;y<colored.cols();y++){
                                  if(colored.get(i,y)[0]>red&&colored.get(i,y)[1]>blue&&colored.get(i,y)[2]<green){
                                        continue;
                                    }
                                    else {
                                        double[] black = {0,0,0};
                                        colored.put(i,y,black);
                                    }

                                }

                            }
                            */

            //  Imgproc.pyrMeanShiftFiltering(gray,gray,dy,dept);



            Imgproc.cvtColor(gray, gray, Imgproc.COLOR_BGR2GRAY);

            //convert the image to black and white does (8 bit)
            Imgproc.Canny(gray, gray, 200, 50);

            //apply gaussian blur to smoothen lines of dots
            Imgproc.GaussianBlur(gray, gray, new Size(5, 5), 5);
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(gray, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            MatOfPoint largCon = contours.get(dy);
            Double areaofcontour = Imgproc.contourArea(contours.get(dy)) ;
            Boolean convex =  true;


            MatOfPoint2f m2 = new MatOfPoint2f(largCon.toArray());
            double  db = Imgproc.arcLength(m2, true);

            int px=0;int py=0;

            Mat m = contours.get(dy).t();
            double h = m.height();
            double w = m.width();



            Mat fulhsv =new Mat();
            Imgproc.cvtColor(orignal,orignal,Imgproc.COLOR_BGR2RGB);
            Imgproc.cvtColor(orignal, fulhsv, Imgproc.COLOR_RGB2HSV);

            double[] hsv = fulhsv.get(py,px);


            Scalar min  = new Scalar(200,200,200);
            Scalar max  = new Scalar(256,256,256);


            Core.inRange(fulhsv, min, max, fulhsv);






/*
            //segment using color
            for(int i=1;i<fulhsv.rows();i++){
                for(int y=1;y<fulhsv.cols();y++){
                    if(fulhsv.get(i,y)[0]>2&&fulhsv.get(i,y)[0]<10&&fulhsv.get(i,y)[1]>120&&fulhsv.get(i,y)[1]<170&&fulhsv.get(i,y)[2]>210&&fulhsv.get(i,y)[2]<250){
                        continue;
                    }
                    else {
                        double[] black = {0,0,0};
                        fulhsv.put(i,y,black);
                    }

                }

            }
*/


            Imgproc.Canny(fulhsv, orignal, 200, 50);
            //apply gaussian blur to smoothen lines of dots
            Imgproc.GaussianBlur(orignal, orignal, new Size(5, 5), 5);
            List<MatOfPoint> contours1 = new ArrayList<MatOfPoint>();
            Imgproc.findContours(orignal, contours1, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            MatOfPoint largCon1 = null;
            Double areaofcontour1 = 0.0;


            largCon1 = contours1.get(0);

            areaofcontour1 = Imgproc.contourArea(contours1.get(0));

            Boolean convex1 =  true;

            Rect boundingrect=Imgproc.boundingRect(largCon1);;


            // checking for gajar only
            for(int i=0;i<contours1.size();i++){

                areaofcontour1 = Imgproc.contourArea(contours1.get(i));
                if(areaofcontour1>1000){

                    largCon1 = contours1.get(i);
                    boundingrect = Imgproc.boundingRect(largCon1);


                    scolor="red";
                    Toast.makeText(ClassImagePro.this, "red done", Toast.LENGTH_SHORT).show();
                    break;
                }



            }

            //  Toast.makeText(ClassImagePro.this, "h: "+hsv[0]+" s: "+hsv[1]+ " v: "+hsv[2], Toast.LENGTH_LONG).show();

            double wedth =   boundingrect.width;
            double hieght= boundingrect.height;

            double ratio ;
            if(wedth>hieght){
                ratio=wedth/hieght;
            }else{
                ratio=hieght/wedth;
            }

            if(ratio<1.25&&areaofcontour1>1000){
                Acircle circlobject = new Acircle();
                if(scolor.equalsIgnoreCase("red")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();


                }else if(scolor.equalsIgnoreCase("green")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("white")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("brown")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("whitegreen")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ClassImagePro.this, " something else ", Toast.LENGTH_SHORT).show();
                }


            }else if(ratio>1.3&&areaofcontour1>1000){

                Blong longObject = new Blong();
                if(scolor.equalsIgnoreCase("yellow")){
                    String vegname= longObject.yellow();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();
                }if(scolor.equalsIgnoreCase("red")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();


                }else if(scolor.equalsIgnoreCase("lightgreen")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();
                    return 1;

                }else if(scolor.equalsIgnoreCase("darkgreen")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("purple")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ClassImagePro.this, " something else ", Toast.LENGTH_SHORT).show();
                }


            }

            // Mat matat30 = contours.get(30).t();


            //   Mat m = contours.get(30).t();
            //finding minimum & maximum xpoint and at a give y point
            //double min=point[0].x,max=point[0].x;



            t1.setText(t1.getText().toString() + " area: " + areaofcontour1 +" db: "+db+" h: "+h+ " w: "+w );
            ArrayList<MatOfPoint> largest_contours = new ArrayList<MatOfPoint>();
            largest_contours.add(largCon1);


            Imgproc.cvtColor(gray, gray, Imgproc.COLOR_BayerBG2RGB);
            Imgproc.drawContours(orignal, largest_contours, -1, new Scalar(255,255,0), 1);


            Mat larg = gray;

            Bitmap img2 = Bitmap.createBitmap(orignal.cols(), orignal.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(orignal, img2);
            i2.setImageBitmap(img2);

        }catch(Exception e){

            Toast.makeText(ClassImagePro.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return -1;
    }
    int cucumber(){

        TextView t1 = (TextView)findViewById(R.id.rawdata);
        t1.setText(" y: " + dy );
        try {

            //gray= Utils.loadResource(ClassImagePro.this, R.drawable.pveg, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
            gray = copy.clone();
            Mat orignal = gray.clone();

            // Imgproc.cvtColor(gray,gray,Imgproc.COLOR_BGR2RGB);
            ///  double[] ar = colored.get(1,1);
            ///  double[] black = {0,0,0};
            // colored.put(1,1,black);
                          /*  red =Double.parseDouble(redd.getText().toString());
                            green =Double.parseDouble(greenn.getText().toString());
                            blue =Double.parseDouble(bluee.getText().toString());
                            //segment using color
                          for(int i=1;i<colored.rows();i++){
                                for(int y=1;y<colored.cols();y++){
                                  if(colored.get(i,y)[0]>red&&colored.get(i,y)[1]>blue&&colored.get(i,y)[2]<green){
                                        continue;
                                    }
                                    else {
                                        double[] black = {0,0,0};
                                        colored.put(i,y,black);
                                    }

                                }

                            }
                            */

            //  Imgproc.pyrMeanShiftFiltering(gray,gray,dy,dept);



            Imgproc.cvtColor(gray, gray, Imgproc.COLOR_BGR2GRAY);

            //convert the image to black and white does (8 bit)
            Imgproc.Canny(gray, gray, 200, 50);

            //apply gaussian blur to smoothen lines of dots
            Imgproc.GaussianBlur(gray, gray, new Size(5, 5), 5);
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(gray, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            MatOfPoint largCon = contours.get(dy);
            Double areaofcontour = Imgproc.contourArea(contours.get(dy)) ;
            Boolean convex =  true;


            MatOfPoint2f m2 = new MatOfPoint2f(largCon.toArray());
            double  db = Imgproc.arcLength(m2, true);

            int px=0;int py=0;

            Mat m = contours.get(dy).t();
            double h = m.height();
            double w = m.width();



            Mat fulhsv =new Mat();
            Imgproc.cvtColor(orignal,orignal,Imgproc.COLOR_BGR2RGB);
            Imgproc.cvtColor(orignal, fulhsv, Imgproc.COLOR_RGB2HSV);

            double[] hsv = fulhsv.get(py,px);


            red =Double.parseDouble(redd.getText().toString());
            green =Double.parseDouble(greenn.getText().toString());
            blue =Double.parseDouble(bluee.getText().toString());
            redg =Double.parseDouble(reddg.getText().toString());
            greeng =Double.parseDouble(greenng.getText().toString());
            blueg =Double.parseDouble(blueeg.getText().toString());


            Scalar min  = new Scalar(2,120,210);
            Scalar max  = new Scalar(10,170,256);


            Core.inRange(fulhsv, min, max, fulhsv);






/*
            //segment using color
            for(int i=1;i<fulhsv.rows();i++){
                for(int y=1;y<fulhsv.cols();y++){
                    if(fulhsv.get(i,y)[0]>2&&fulhsv.get(i,y)[0]<10&&fulhsv.get(i,y)[1]>120&&fulhsv.get(i,y)[1]<170&&fulhsv.get(i,y)[2]>210&&fulhsv.get(i,y)[2]<250){
                        continue;
                    }
                    else {
                        double[] black = {0,0,0};
                        fulhsv.put(i,y,black);
                    }

                }

            }
*/


            Imgproc.Canny(fulhsv, orignal, 200, 50);
            //apply gaussian blur to smoothen lines of dots
            Imgproc.GaussianBlur(orignal, orignal, new Size(5, 5), 5);
            List<MatOfPoint> contours1 = new ArrayList<MatOfPoint>();
            Imgproc.findContours(orignal, contours1, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            MatOfPoint largCon1 = null;
            Double areaofcontour1 = 0.0;


            largCon1 = contours1.get(0);

            areaofcontour1 = Imgproc.contourArea(contours1.get(0));

            Boolean convex1 =  true;

            Rect boundingrect=Imgproc.boundingRect(largCon1);;


            // checking for gajar only
            for(int i=0;i<contours1.size();i++){

                areaofcontour1 = Imgproc.contourArea(contours1.get(i));
                if(areaofcontour1>1000){

                    largCon1 = contours1.get(i);
                    boundingrect = Imgproc.boundingRect(largCon1);


                    scolor="red";
                    Toast.makeText(ClassImagePro.this, "red done", Toast.LENGTH_SHORT).show();
                    break;
                }



            }

            //  Toast.makeText(ClassImagePro.this, "h: "+hsv[0]+" s: "+hsv[1]+ " v: "+hsv[2], Toast.LENGTH_LONG).show();

            double wedth =   boundingrect.width;
            double hieght= boundingrect.height;

            double ratio ;
            if(wedth>hieght){
                ratio=wedth/hieght;
            }else{
                ratio=hieght/wedth;
            }

            if(ratio<1.25&&areaofcontour1>1000){
                Acircle circlobject = new Acircle();
                if(scolor.equalsIgnoreCase("red")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();


                }else if(scolor.equalsIgnoreCase("green")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("white")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("brown")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("whitegreen")){
                    String vegname= circlobject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ClassImagePro.this, " something else ", Toast.LENGTH_SHORT).show();
                }


            }else if(ratio>1.3&&areaofcontour1>1000){

                Blong longObject = new Blong();
                if(scolor.equalsIgnoreCase("yellow")){
                    String vegname= longObject.yellow();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();
                }if(scolor.equalsIgnoreCase("red")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();
                    return 1;

                }else if(scolor.equalsIgnoreCase("lightgreen")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("darkgreen")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else if(scolor.equalsIgnoreCase("purple")){
                    String vegname= longObject.red();
                    Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ClassImagePro.this, " something else ", Toast.LENGTH_SHORT).show();
                }


            }

            // Mat matat30 = contours.get(30).t();


            //   Mat m = contours.get(30).t();
            //finding minimum & maximum xpoint and at a give y point
            //double min=point[0].x,max=point[0].x;



            t1.setText(t1.getText().toString() + " area: " + areaofcontour1 +" db: "+db+" h: "+h+ " w: "+w );
            ArrayList<MatOfPoint> largest_contours = new ArrayList<MatOfPoint>();
            largest_contours.add(largCon1);


            Imgproc.cvtColor(gray, gray, Imgproc.COLOR_BayerBG2RGB);
            Imgproc.drawContours(orignal, largest_contours, -1, new Scalar(255,255,0), 1);


            Mat larg = gray;

            Bitmap img2 = Bitmap.createBitmap(orignal.cols(), orignal.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(orignal, img2);
            i2.setImageBitmap(img2);

        }catch(Exception e){

            Toast.makeText(ClassImagePro.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return -1;
    }
  int potato(){


      TextView t1 = (TextView)findViewById(R.id.rawdata);
      t1.setText(" y: " + dy);
      try {

          //reading the picture into a mat
         // gray= Utils.loadResource(ClassImagePro.this, R.drawable.shapeskela, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
          gray = copy;
          CascadeClassifier Cc ;

          Cc = new CascadeClassifier();





          //changing color to hsv
          Mat fulhsv =gray.clone();
         // Imgproc.cvtColor(fulhsv,fulhsv,Imgproc.COLOR_BGR2RGB);
         // Imgproc.cvtColor(fulhsv, fulhsv, Imgproc.COLOR_RGB2HSV);


          //values for reg in range
         red =Double.parseDouble(redd.getText().toString());
         green =Double.parseDouble(greenn.getText().toString());
          blue =Double.parseDouble(bluee.getText().toString());
          redg =Double.parseDouble(reddg.getText().toString());
           greeng =Double.parseDouble(greenng.getText().toString());
          blueg =Double.parseDouble(blueeg.getText().toString());


            //filtering color
         // Scalar min  = new Scalar(2,120,210);
         // Scalar max  = new Scalar(10,170,256);
         // Core.inRange(fulhsv, min, max, fulhsv);


            //applying canny edge
          Imgproc.Canny(fulhsv, fulhsv, 200, 50);





          //listing countours
          List<MatOfPoint> contours1 = new ArrayList<MatOfPoint>();
          Imgproc.findContours(fulhsv, contours1,new Mat() , Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);



          MatOfPoint largCon1 = null;
          Double areaofcontour1 = 0.0;

            //assuming the first countor to be the largest
          largCon1 = contours1.get(dy);
          areaofcontour1 = Imgproc.contourArea(contours1.get(dy));

          Boolean convex1 =  true;
          Rect boundingrect;
          double hieght=0.0;
          double wedth=0.0;
          double ratio =0.0;
          int j =0;


          //refinning the countours



          scolor="brown";
          Toast.makeText(ClassImagePro.this, "brown done", Toast.LENGTH_SHORT).show();



          checkRatioOf(ratio,areaofcontour1);

          t1.setText(t1.getText().toString() + " area: " + areaofcontour1 +" db: "+" h: "+ " w: " );


          ArrayList<MatOfPoint> largest_contours = new ArrayList<MatOfPoint>();
          largest_contours.add(largCon1);

          ///drawing the countours
          Imgproc.drawContours(gray, largest_contours, -1, new Scalar(255,255,0), 1);

          //converting the mat image to bitmap for displaying
          Bitmap img2 = Bitmap.createBitmap(fulhsv.cols(), fulhsv.rows(), Bitmap.Config.ARGB_8888);
          Utils.matToBitmap(fulhsv, img2);

          //displaying the bitmap on a view
          i2.setImageBitmap(img2);

      }catch(Exception e){

          Toast.makeText(ClassImagePro.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
      }

return -1;
  }
    void haarcascad(){



        InputStream is  =  null;
        FileOutputStream os = null;
        File mCascadeFile = null;
        File cascadeDir = null;



        try {

           // gray = Utils.loadResource(ClassImagePro.this, R.drawable.ban4, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
            gray=copy;
        }catch (Exception e) {
            Toast.makeText(ClassImagePro.this, " gray load: exception ", Toast.LENGTH_SHORT).show();

        }

            Mat rgb = gray.clone();


try {
    Imgproc.cvtColor(gray, gray, Imgproc.COLOR_BGR2GRAY);
}catch (Exception e){
    Toast.makeText(ClassImagePro.this, " color coversion problem "+e.getMessage(), Toast.LENGTH_SHORT).show();

}
            try {

                is = getResources().getAssets().open("casban.xml");
                cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
                mCascadeFile = new File(cascadeDir, "casban.xml");
                os = new FileOutputStream(mCascadeFile);




        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }

        is.close();
        os.close();

    }catch (Exception e) {
        Toast.makeText(ClassImagePro.this, " casban.xml load exception ", Toast.LENGTH_SHORT).show();

    }
        CascadeClassifier Cc = null;

        try {


            Cc = new CascadeClassifier(mCascadeFile.getAbsolutePath());


        }catch (Exception e) {
            Toast.makeText(ClassImagePro.this, " apply cascad exception occurred ", Toast.LENGTH_SHORT).show();

        }


            if (Cc.empty()) {
                Toast.makeText(ClassImagePro.this, " failed to load cascade ", Toast.LENGTH_SHORT).show();
                Cc = null;
            } else
                Toast.makeText(ClassImagePro.this, " Loaded cascade ", Toast.LENGTH_SHORT).show();

        try{
            cascadeDir.delete();


        }catch (Exception e) {
            Toast.makeText(ClassImagePro.this, " dir delete ", Toast.LENGTH_SHORT).show();

        }


            try{



            MatOfRect ban = new MatOfRect();

            if (Cc != null)
                Cc.detectMultiScale(gray, ban, 1.1, 2, 2,new Size(100, 100), new Size());

                  Imgproc.cvtColor(rgb, rgb, Imgproc.COLOR_BGR2RGB);



        Rect[] facesArray = ban.toArray();

        for (int i = 0; i < facesArray.length; i++)
            Imgproc.rectangle(rgb, facesArray[i].tl(), facesArray[i].br(),new Scalar(100, 100, 100, 150), 3);



                //converting the mat image to bitmap for displaying
                Bitmap img2 = Bitmap.createBitmap(rgb.cols(), rgb.rows(), Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(rgb, img2);

                //displaying the bitmap on a view
                i2.setImageBitmap(img2);


                is.close();
                os.close();
            //    rgb.release();
              //  copy.release();
             //   gray.release();
             //   ban.release();



        } catch (Exception e) {
            Toast.makeText(ClassImagePro.this, " bitmap and rect draw problem ", Toast.LENGTH_SHORT).show();
        }



    }

    private void freeupmemory() {


    }

    private void checkRatioOf(double ratio,double areaofcontour1) {

        if(ratio<1.3&&areaofcontour1>1000){
            Acircle circlobject = new Acircle();
            if(scolor.equalsIgnoreCase("red")){
                String vegname= circlobject.red();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else if(scolor.equalsIgnoreCase("green")){
                String vegname= circlobject.green();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else if(scolor.equalsIgnoreCase("white")){
                String vegname= circlobject.white ();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else if(scolor.equalsIgnoreCase("brown")){
                String vegname= circlobject.brown();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else if(scolor.equalsIgnoreCase("whitegreen")){
                String vegname= circlobject.whitegreen();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();


            }else {
                Toast.makeText(ClassImagePro.this, " something else ", Toast.LENGTH_SHORT).show();
            }


        }else if(ratio>1.5&&areaofcontour1>1000){

            Blong longObject = new Blong();
            if(scolor.equalsIgnoreCase("yellow")){
                String vegname= longObject.yellow();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();
            }if(scolor.equalsIgnoreCase("red")){
                String vegname= longObject.red();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else if(scolor.equalsIgnoreCase("lightgreen")){
                String vegname= longObject.red();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else if(scolor.equalsIgnoreCase("darkgreen")){
                String vegname= longObject.red();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else if(scolor.equalsIgnoreCase("purple")){
                String vegname= longObject.red();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(ClassImagePro.this, " something else ", Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(ClassImagePro.this, " no item found ", Toast.LENGTH_SHORT).show();
        }


    }

    private Mat findLargestRectangle(Mat original_image) {
        Mat imgSource = original_image;

        //convert the image to black and white
        Imgproc.cvtColor(imgSource, imgSource, Imgproc.COLOR_BGR2GRAY);

        //convert the image to black and white does (8 bit)
        Imgproc.Canny(imgSource, imgSource, 50, 50);

        //apply gaussian blur to smoothen lines of dots
        Imgproc.GaussianBlur(imgSource, imgSource, new Size(5, 5), 5);

        //find the contours
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(imgSource, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        double maxArea = -1;
        int maxAreaIdx = -1;
        MatOfPoint temp_contour = contours.get(0); //the largest is at the index 0 for starting point
        MatOfPoint2f approxCurve = new MatOfPoint2f();
        Mat largest_contour = contours.get(0);
        List<MatOfPoint> largest_contours = new ArrayList<MatOfPoint>();
        for (int idx = 0; idx < contours.size(); idx++) {
            temp_contour = contours.get(idx);
            double contourarea = Imgproc.contourArea(temp_contour);
            //compare this contour to the previous largest contour found
            if (contourarea > maxArea) {
                //check if this contour is a square
                MatOfPoint2f new_mat = new MatOfPoint2f( temp_contour.toArray() );
                int contourSize = (int)temp_contour.total();
                Imgproc.approxPolyDP(new_mat, approxCurve, contourSize*0.05, true);
                if (approxCurve.total() == 4) {
                    maxArea = contourarea;
                    maxAreaIdx = idx;
                    largest_contours.add(temp_contour);
                    largest_contour = temp_contour;
                }
            }
        }

        MatOfPoint temp_largest = largest_contours.get(largest_contours.size()-1);
        largest_contours = new ArrayList<MatOfPoint>();
        largest_contours.add(temp_largest);

        Imgproc.cvtColor(imgSource, imgSource, Imgproc.COLOR_BayerBG2RGB);
        Imgproc.drawContours(imgSource, largest_contours, -1, new Scalar(0, 255, 0), 1);

        //create the new image here using the largest detected square

        Toast.makeText(getApplicationContext(), "Largest Contour: ", Toast.LENGTH_LONG).show();

        return imgSource;
    }


    int finaltest(double ratio, double areaofcontour){

        if(ratio<1.3&&areaofcontour>1000){
            Acircle circlobject = new Acircle();
            if(scolor.equalsIgnoreCase("red")){
                String vegname= circlobject.red();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else if(scolor.equalsIgnoreCase("green")){
                String vegname= circlobject.red();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else if(scolor.equalsIgnoreCase("white")){
                String vegname= circlobject.red();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else if(scolor.equalsIgnoreCase("brown")){
                String vegname= circlobject.red();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else if(scolor.equalsIgnoreCase("whitegreen")){
                String vegname= circlobject.red();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(ClassImagePro.this, " something else ", Toast.LENGTH_SHORT).show();
            }


        }else if(ratio>1.3&&areaofcontour>1000){

            Blong longObject = new Blong();
            if(scolor.equalsIgnoreCase("yellow")){
                String vegname= longObject.yellow();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

// for popup rates
                int i = 0;
                while (i < dbaccess.datahub.itemslist.size()) {
                    if (dbaccess.datahub.itemslist.get(i).equalsIgnoreCase("Banana")) {
                      String  item = dbaccess.datahub.itemslist.get(i);
                      String  itemrate = dbaccess.datahub.itemsrates.get(i);
                        //  Toast.makeText(context, "" + result[position] + " Costs " + itemrate, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, popup.class);
                        intent.putExtra("rate",itemrate);
                        intent.putExtra("item", item);

                        this.startActivity(intent);

                        break;
                    }
                    i++;

                }


                Bitmap img2 = Bitmap.createBitmap(orignal.cols(), orignal.rows(), Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(orignal, img2);
                i2.setImageBitmap(img2);

                return 1;
            }else if(scolor.equalsIgnoreCase("red")){
                String vegname= longObject.red();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else if(scolor.equalsIgnoreCase("lightgreen")){
                String vegname= longObject.red();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else if(scolor.equalsIgnoreCase("darkgreen")){
                String vegname= longObject.red();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else if(scolor.equalsIgnoreCase("purple")){
                String vegname= longObject.red();
                Toast.makeText(ClassImagePro.this, ""+vegname, Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(ClassImagePro.this, " something else! ", Toast.LENGTH_SHORT).show();
            }


        }

        return  0;
    }


/* for template matching but its not usefull for now
    public void run(int match_method) {


        Mat img = copy.clone();
        Mat templ = gray.clone();

        // / Create the result matrix
        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

        // / Do the Matching and Normalize
        Imgproc.matchTemplate(img, templ, result, match_method);
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        // / Localizing the best match with minMaxLoc
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result.clone());

        Point matchLoc;
        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
            matchLoc = mmr.minLoc;
        } else {
            matchLoc = mmr.maxLoc;
        }

        // / Show me what you got
        Imgproc.rectangle(img, matchLoc, new Point(matchLoc.x + templ.cols(),
                matchLoc.y + templ.rows()), new Scalar(0, 255, 0));


        // Save the visualized detection.
        Bitmap img2 = Bitmap.createBitmap(img.cols(), img.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(img, img2);
        i2.setImageBitmap(img2);
        Toast.makeText(ClassImagePro.this, " Detected feature", Toast.LENGTH_SHORT).show();

    }

*/
}
