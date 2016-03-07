package com.example.sahil.try3;


import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class connection extends Activity
{
    // Variables declaration - do not modify
    Button b_anonymous;
    Button b_connect;
    Button b_disconnect;
    Button b_send;
    //ScrollPane jScrollPane1;
    TextView lb_address,lb_name,lb_password,lb_port,lb_username;
    EditText ta_chat= new EditText(this);
    EditText tf_address = new EditText(this);
    EditText tf_chat=new EditText(this);
    EditText tf_password=new EditText(this);
    EditText tf_port = new EditText(this);
    EditText tf_username = new EditText(this);
    // End of variables declaration
    String[] data;




    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;

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
        ta_chat.append(data + " is now offline.\n");
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
            ta_chat.append("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//

    public void Disconnect()
    {
        try
        {
            ta_chat.append("Disconnected.\n");
            sock.close();
        } catch(Exception ex) {
            ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
   //     tf_username.setEditable(true);

    }

    public connection()
    {

    }

    //--------------------------//

    public class IncomingReader implements Runnable
    {
        @Override
        public void run()
        {

            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", update = "Update";

            try
            {
                while ((stream = reader.readLine()) != null)
                {
                    data = stream.split(":");

                    if (data[2].equals(update))
                    {
                        ta_chat.append(data[0] + ": " + data[1] + "\n");
                   //     ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                    }
                    else if (data[2].equals(connect))
                    {
                  //      ta_chat.removeAll();
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







    private void b_connectActionPerformed() {
        if (isConnected == false)
        {
         //   username = tf_username.getText();
         //   tf_username.setEditable(false);

            try
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush();
                isConnected = true;
            }
            catch (Exception ex)
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
          //      tf_username.setEditable(true);
            }

            ListenThread();

        } else if (isConnected == true)
        {
            ta_chat.append("You are already connected. \n");
        }
    }



    private void b_anonymousActionPerformed() {
     //   tf_username.setText("");
        if (isConnected == false)
        {
            String anon="anon";
            Random generator = new Random();
            int i = generator.nextInt(999) + 1;
            String is=String.valueOf(i);
            anon=anon.concat(is);
            username=anon;

           // tf_username.setText(anon);
         //   tf_username.setEditable(false);

            try
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(anon + ":has connected.:Connect");
                writer.flush();
                isConnected = true;
            }
            catch (Exception ex)
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
              //  tf_username.setEditable(true);
            }

            ListenThread();

        } else if (isConnected == true)
        {
            ta_chat.append("You are already connected. \n");
        }
    }

    private void b_sendActionPerformed() {
        String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
            try {
                writer.println(username + ":" + tf_chat.getText() + ":" + "Chat");
                writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                ta_chat.append("Message was not sent. \n");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
    }




}

