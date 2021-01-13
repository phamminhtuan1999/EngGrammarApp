package com.ductho.nguphaptienganh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.ductho.nguphaptienganh.R;

public class SplashScreenActivity extends AppCompatActivity {

    public static final int duration = 3000;
    SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mSharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        splashScreenStart();

    }

    public void splashScreenStart() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSharedPreferences.getBoolean("isFirstTime", true)) {
                    ;
                    startActivity(new Intent(SplashScreenActivity
                            .this, OnBoardActivity.class));

                }
                else{
                    startActivity(new Intent(SplashScreenActivity
                            .this, LoginActivity.class));
                }
                finish();
            }
        }, duration);

    }
}