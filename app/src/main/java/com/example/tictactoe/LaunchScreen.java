package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class LaunchScreen extends AppCompatActivity {

    //After completion of 2000 ms, the next activity will get started.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launch_screen);

        int SPLASH_SCREEN_TIME_OUT = 5000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(LaunchScreen.this, LoginActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}