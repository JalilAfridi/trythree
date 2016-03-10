package com.example.sahil.try3;

import android.util.Log;
import android.widget.Toast;

import java.util.Random;


        import java.net.*;
        import java.io.*;
        import java.util.*;

public class connection
{
    String username, address = "192.168.10.2";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;
    String string="null";
    single dbaccess = single.getInstance();

    Socket sock;
    BufferedReader reader;
    PrintWriter writer;

    //--------------------------//

    public void ListenThread()
    {
        Thread IncomingReader = new Thread(new IncomingReader());
        IncomingReader.start();
    }

    //--------------------------//

    public void userAdd(String data)
    {
        users.add(data);
    }

    //--------------------------//

    public void userRemove(String data)
    {
        Log.i("test",data + " is now offline.\n");
    }

    //--------------------------//

    public void writeUsers()
    {
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        for (String token:tempList)
        {
            //users.append(token + "\n");
        }
    }

    //--------------------------//

    public void sendDisconnect()
    {
        String bye = (username + ": :Disconnect");
        try
        {
            writer.println(bye);
            writer.flush();
        } catch (Exception e)
        {
            Log.i("test","Could not send Disconnect message.\n");
        }
    }

    //--------------------------//

    public void Disconnect()
    {
        try
        {
            Log.i("test","Disconnected.\n");
            sock.close();
        } catch(Exception ex) {
            Log.i("test","Failed to disconnect. \n");
        }
        isConnected = false;


    }




    public class IncomingReader implements Runnable
    {
        @Override
        public void run()
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", update = "Update";

            try
            {
                while ((stream = reader.readLine()) != null)
                {
                    data = stream.split(":");

                    if (data[2].equals(update))
                    {
                        string = data[1];
                        String[] rates = string.split(", ");
                        Log.i("Rate",string);
                        for(int i = 0; i<rates.length;i++) {
                            dbaccess.datahub.itemsrates.set(i,rates[i]);
                        }


                        Log.i("output",data[1]);
                    }
                    else if (data[2].equals(connect))
                    {

                        userAdd(data[0]);
                    }
                    else if (data[2].equals(disconnect))
                    {
                        userRemove(data[0]);
                    }
                    else if (data[2].equals(done))
                    {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                    }
                }
            }catch(Exception ex) { }
        }
    }

    //--------------------------//



    void startconnection(){
        Thread con = new Thread(new starter());
        con.start();
        if(isConnected==true){

        }
    }


    public class starter implements Runnable {
        @Override
        public void run(){

        if(isConnected==false)

        {
            String anon = "anon";
            Random generator = new Random();
            int i = generator.nextInt(999) + 1;
            String is = String.valueOf(i);
            anon = anon.concat(is);
            username = anon;


            try {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(anon + ":has connected.:Connect");
                writer.flush();
                isConnected = true;
            } catch (Exception ex) {
                Log.i("test", "Cannot Connect! Try Again. ");
                ex.printStackTrace();

            }

            ListenThread();

        }

        else if(isConnected==true)

        {
            Log.i("test", "You are already connected. ");
        }

    }

    }




}





