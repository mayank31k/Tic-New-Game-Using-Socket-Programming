package com.rawggar.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Time;

public class Game extends AppCompatActivity implements View.OnClickListener {
    String name,ip;
    int id,latest;
    private boolean end = false;
    Button restart;
    TextView gameName,turn;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    Button[] buttonarray;
    Boolean[] played;
    int[] array;
    int chance=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        id=getIntent().getExtras().getInt("id");
        name=getIntent().getStringExtra("Name");
        restart=findViewById(R.id.restart);
        gameName=findViewById(R.id.gameName);
        gameName.setText("Tic Tac Toe");
        turn=findViewById(R.id.turn);
        turn.setText("server's turn");
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);

        buttonarray= new Button[]{btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9};
        played = new Boolean[]{false,false,false,false,false,false,false,false,false,false};
        array=new int[]{0,0,0,0,0,0,0,0,0};

        //Toast.makeText(getApplicationContext(),"Inside oncreate "+id,Toast.LENGTH_SHORT).show();

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        restart.setOnClickListener(this);
        Log.d("ashish","inside oncreate");
        if(id==1)
        {
            //Log.d("ashish","i am server");
            startServerSocket();
        }

        if(id==2)
        {      // Log.d("ashish","i am client");
            sendMessage();
        }

    }



    @Override
    public void onClick(View v) {

            switch (v.getId()) {

                case R.id.btn1: latest=1;

                    break;

                case R.id.btn2:latest=2;

                    break;

                case R.id.btn3:latest=3;

                    break;

                case R.id.btn4:latest=4;

                    break;

                case R.id.btn5:latest=5;

                    break;

                case R.id.btn6:latest=6;

                    break;

                case R.id.btn7:latest=7;
                    break;

                case R.id.btn8:latest=8;
                    break;

                case R.id.btn9:latest=9;
                    break;

                case R.id.restart: start();
                break;

            }
        updateUI(id,latest);

    }

    private void start() {

        for(int i=0;i<9;i++)
        {
            Button b=buttonarray[i];
            b.setBackgroundResource(0);
            array[i]=0;
            played[i]=false;
        }
        latest=0;
        chance=1;

    }

    private void updateUI(final int id,final int last) {

        if (chance == id) {
            String t = "server";
            if (last != 0) {
                if (played[last - 1] == false) {
                    Button b = buttonarray[last - 1];

                    if (id == 1) {
                        b.setBackgroundResource(R.drawable.cross);
                        t = "client's turn";
                        chance=2;
                        array[last-1]=1;
                    }
                    if (id == 2) {
                        b.setBackgroundResource(R.drawable.zero);
                        t = "server's turn";
                        chance=1;
                        array[last-1]=2;
                    }
                    played[last - 1] = true;
                    checker();
                    turn.setText(t);
                    Log.d("ashish", last + " by " + id);


                }
            }
        }
    }

    private void checker() {
        int winner=0;

        if(array[0]==array[1]&&array[0]==array[2])
        {
            if(array[0]==1)
            {
                winner=1;
            }
            if(array[0]==2)
            {
                winner=2;
            }
        }



        if(array[3]==array[4]&&array[4]==array[5])
        {
            if(array[3]==1)
            {
                winner=1;
            }
            if(array[3]==2)
            {
                winner=2;
            }
        }


        if(array[6]==array[7]&&array[7]==array[8])
        {
            if(array[6]==1)
            {
                winner=1;
            }
            if(array[6]==2)
            {
                winner=2;
            }
        }


        if(array[0]==array[3]&&array[0]==array[6])
        {
            if(array[0]==1)
            {
                winner=1;
            }
            if(array[0]==2)
            {
                winner=2;
            }
        }



        if(array[4]==array[1]&&array[4]==array[7])
        {
            if(array[1]==1)
            {
                winner=1;
            }
            if(array[1]==2)
            {
                winner=2;
            }
        }


        if(array[2]==array[5]&&array[8]==array[2])
        {
            if(array[2]==1)
            {
                winner=1;
            }
            if(array[2]==2)
            {
                winner=2;
            }
        }



        if(array[0]==array[4]&&array[0]==array[8])
        {
            if(array[0]==1)
            {
                winner=1;
            }
            if(array[0]==2)
            {
                winner=2;
            }
        }



        if(array[2]==array[4]&&array[6]==array[2])
        {
            if(array[2]==1)
            {
                winner=1;
            }
            if(array[2]==2)
            {
                winner=2;
            }
        }


        if(winner!=0)
        {
            String win="";
            if(winner==1)
                win="Server";
            if(winner==2)
                win="Client";

            AlertDialog.Builder builder1 = new AlertDialog.Builder(Game.this);
            builder1.setMessage("The Winner Is "+win);
            builder1.setCancelable(true);

            AlertDialog alert11 = builder1.create();
            alert11.show();

        }


    }

    private void startServerSocket() {
        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {

            private String stringData = null;

            @Override
            public void run() {

                try {

                    ServerSocket ss = new ServerSocket(9002);
                    //Log.d("ashish","got message from server");
                    while (!end) {

                        Socket s = ss.accept();
                        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        PrintWriter output = new PrintWriter(s.getOutputStream());

                        stringData = input.readLine();


                        //updateUI(2,Integer.parseInt(stringData));

                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                updateUI(2,Integer.parseInt(stringData));
                            }
                        });
                        output.println(latest);

                        output.flush();

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //Log.d("output,input,inputline",output+ " "+ input+" "+stringData);
                        output.close();
                        s.close();
                    }
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        thread.start();
    }




    private void sendMessage() {

        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                         Socket s = new Socket(ip, 9002);

                        OutputStream out = s.getOutputStream();

                        PrintWriter output = new PrintWriter(out);

                        output.println(latest);
                        output.flush();
                        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        final String st = input.readLine();
                        //Log.d("output,input,inputline", out + " " + input + " " + st);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                updateUI(1,Integer.parseInt(st));
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        output.close();
                        out.close();
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }

}
