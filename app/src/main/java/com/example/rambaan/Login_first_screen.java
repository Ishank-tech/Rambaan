package com.example.rambaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login_first_screen extends AppCompatActivity {

    TextInputLayout email,pass;
    TextView forgetPass;
    Button login;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_first_screen);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        forgetPass = findViewById(R.id.forget_pass);
        login = findViewById(R.id.login_btn);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        forgetPass.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetPass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateEmail() | !validatePassword()){
                    return;
                }

                String _email = email.getEditText().getText().toString().trim();
                String _pass = pass.getEditText().getText().toString().trim();

                Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("email").equalTo(_email);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            email.setError(null);
                            email.setErrorEnabled(false);

                            String systemPassword = snapshot.child(_email).child("Password").getValue(String.class);
                            if(systemPassword.equals(_pass)){
                                pass.setError(null);
                                pass.setErrorEnabled(false);

                                String _fullname = snapshot.child(_email).child("fullname").getValue(String.class);
                                String _username = snapshot.child(_email).child("username").getValue(String.class);
                                String _dob = snapshot.child(_email).child("dob").getValue(String.class);
                                String _phoneNo = snapshot.child(_email).child("phoneNo").getValue(String.class);
                                String _gender = snapshot.child(_email).child("gender").getValue(String.class);

                                Intent intent = new Intent(Login_first_screen.this,MainActivity.class);

                                startActivity(intent);

                            }
                            else{
                                Toast.makeText(Login_first_screen.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Login_first_screen.this, "No such user exist!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Login_first_screen.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public void back2(View view){
        Intent intent = new Intent(Login_first_screen.this,Startup_for_login_signup.class);
        startActivity(intent);
        finish();
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
        String val = pass.getEditText().getText().toString().trim();
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
            pass.setError("Field cannot be empty!");
            return false;
        }
        else if(!val.matches(checkPassword)){
            pass.setError("Password is too weak!");
            return false;
        }
        else{
            pass.setError(null);
            pass.setErrorEnabled(false);
            return true;
        }

    }

}