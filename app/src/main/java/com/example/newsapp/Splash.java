package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class Splash extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


       Handler hd =new Handler();

        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent (Splash.this,MainActivity.class);

            }


        },1000);


    }}
