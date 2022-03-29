package com.example.rambaan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView logo;
    TextView textView, head;
    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        logo = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView2);
        head = findViewById(R.id.textView);

        logo.animate().translationY(-800).setDuration(500).setStartDelay(2000);
        textView.animate().translationY(700).setDuration(500).setStartDelay(2000);
        head.animate().translationY(700).setDuration(500).setStartDelay(2000);

        Thread thread = new Thread(){

            public void run(){

                try{

                    sleep(2000);

                }
                catch(Exception e){

                    e.printStackTrace();

                }
                finally{

                    onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                    boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

                    if(isFirstTime){
                        SharedPreferences.Editor editor = onBoardingScreen.edit();
                        editor.putBoolean("firstTime",false);
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(),Onboarding.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(SplashScreenActivity.this , MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }

            }

        }; thread.start();
    }
}