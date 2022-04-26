package com.example.rambaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Startup_for_login_signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_for_login_signup);
        getSupportActionBar().hide();


    }
    public void Signup(View view){
        Intent intent = new Intent(Startup_for_login_signup.this,Signup_first_screen.class);
        startActivity(intent);
    }

    public void Login(View view){
        Intent intent = new Intent(Startup_for_login_signup.this,Login_first_screen.class);
        startActivity(intent);
    }

}