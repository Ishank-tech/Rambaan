package com.example.rambaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

public class Signup_first_screen extends AppCompatActivity {

    TextInputLayout fullname, email, username, password;
    Button nextScreen;
//    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_first_screen);

        fullname = (TextInputLayout) findViewById(R.id.signup_full_name);
        email = (TextInputLayout)findViewById(R.id.signup_email);
        username = (TextInputLayout)findViewById(R.id.signup_username);
        password = (TextInputLayout)findViewById(R.id.signup_password);
        nextScreen = findViewById(R.id.next_screen2);
//        backBtn = findViewById(R.id.back_btn2);
        nextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateFullname() | !validateEmail() | !validatePassword() | !validateUsername()){
                    return;
                }

                String _fullname = fullname.getEditText().getText().toString();
                String _email = email.getEditText().getText().toString();
                String _username = username.getEditText().getText().toString();
                String _pass = password.getEditText().getText().toString();

                Intent in = new Intent(getApplicationContext(),nextSignupScreen.class);
                in.putExtra("Name",_fullname);
                in.putExtra("Email",_email);
                in.putExtra("Username",_username);
                in.putExtra("Password",_pass);
                startActivity(in);
            }
        });
    }

    /*public void NxtScreen(View view){

        if(!validateFullname() | !validateEmail() | validatePassword() | validateUsername()){
            return;
        }

        String _fullname = fullname.getEditText().getText().toString();
        String _email = email.getEditText().getText().toString();
        String _username = username.getEditText().getText().toString();
        String _pass = password.getEditText().getText().toString();

        Intent in = new Intent(getApplicationContext(),nextSignupScreen.class);
        in.putExtra("Name",_fullname);
        in.putExtra("Email",_email);
        in.putExtra("Username",_username);
        in.putExtra("Password",_pass);
        startActivity(in);
    }*/

//    public void BackToStartup(View view){
//        Intent intent = new Intent(Signup_first_screen.this,Startup_for_login_signup.class);
//        startActivity(intent);
//    }

    private boolean validateFullname(){
        String val = fullname.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            fullname.setError("Field cannot be empty!");
            return false;
        }
        else{
            fullname.setError(null);
            fullname.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateEmail(){
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            email.setError("Field cannot be empty!");
            return false;
        }
        else if(!val.matches(checkEmail)){
            email.setError("Invalid Email!");
            return false;
        }
        else{
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword(){
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^"+
                //"(?=.*[a-z])"+
                "(?=.*[0-9])"+
                //"(?=.*[A-Z])"+
                "(?=.*[a-zA-Z])"+
                "(?=.*[@#$%^&+=])"+
                //"(?=\\S+$)"+
                ".{4,}"+
                "$";

        if(val.isEmpty()){
            password.setError("Field cannot be empty!");
            return false;
        }
        else if(!val.matches(checkPassword)){
            password.setError("Password is too weak!");
            return false;
        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateUsername(){
        String val = username.getEditText().getText().toString();
        String checkspaces = "\\A\\w{4,20}\\z";

        if(val.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()>=20){
            username.setError("Username is too large!");
            return false;
        }
        else if(!val.matches(checkspaces)){
            username.setError("No whitespaces are allowed!");
            return false;
        }
        else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

}