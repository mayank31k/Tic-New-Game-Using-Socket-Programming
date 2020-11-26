package com.rawggar.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText inputip,inputname;

    String ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputip=findViewById(R.id.inputip);
        inputname=findViewById(R.id.inputname);

    }

    public void start(View view) {
        Intent intent=new Intent(this,Game.class);
        intent.putExtra("Name",inputname.getText());
        intent.putExtra("id",1);
        startActivity(intent);
    }

    public void join(View view) {
        ip=inputip.getText().toString();
        Intent intent=new Intent(this,Game.class);
        intent.putExtra("Name",inputname.getText().toString());
        intent.putExtra("ip",inputip.getText().toString());
        intent.putExtra("id",2);
        startActivity(intent);
    }


}
