package com.example.rambaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login_first_screen extends AppCompatActivity {

    TextInputLayout phone,pass;
    TextView forgetPass;
    Button login;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_first_screen);

        phone = findViewById(R.id.phone);
//        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        forgetPass = findViewById(R.id.forget_pass);
        login = findViewById(R.id.login_btn);

//        email.setTranslationX(800);
        phone.setTranslationX(800);
        pass.setTranslationX(800);
        forgetPass.setTranslationX(800);
        login.setTranslationX(800);

//        email.setAlpha(v);
        phone.setAlpha(v);
        pass.setAlpha(v);
        forgetPass.setAlpha(v);
        login.setAlpha(v);

        phone.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validatePhoneNo() | !validatePassword()){
                    return;
                }

                String _phone = phone.getEditText().getText().toString().trim();
                String _pass = pass.getEditText().getText().toString().trim();

//                String _completePhone = "+91"+_phone;

                Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNo").equalTo(_phone);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            phone.setError(null);
                            phone.setErrorEnabled(false);

                            String systemPassword = snapshot.child(_phone).child("password").getValue(String.class);
                            if(systemPassword.equals(_pass)){
                                pass.setError(null);
                                pass.setErrorEnabled(false);

                                String _fullname = snapshot.child(_phone).child("fullName").getValue(String.class);
                                String _username = snapshot.child(_phone).child("username").getValue(String.class);
                                String _dob = snapshot.child(_phone).child("dob").getValue(String.class);
                                String _phoneNo = snapshot.child(_phone).child("phoneNo").getValue(String.class);
                                String _gender = snapshot.child(_phone).child("gender").getValue(String.class);
                                String email = snapshot.child(_phone).child("email").getValue(String.class);
//
//                                Fragment fragment = new AccountFragment();
//
//                                Bundle bundle = new Bundle();
//
//                                bundle.putString("Fullname",_fullname);
//                                bundle.putString("Username",_username);
//                                bundle.putString("Dob",_dob);
//                                bundle.putString("PhoneNo",_phoneNo);
//                                bundle.putString("Gender",_gender);
//                                bundle.putString("Email",email);
//                                bundle.putString("Password",systemPassword);
//                                fragment.setArguments(bundle);

//                                Intent intent = new Intent(Login_first_screen.this, MainActivity.class);
//                                startActivity(intent);

//                                getSupportFragmentManager().beginTransaction().replace(R.id.login,fragment).commit();

                                Intent intent = new Intent(Login_first_screen.this,MainActivity.class);
                                intent.putExtra("Fullname",_fullname);
                                intent.putExtra("Username",_username);
                                intent.putExtra("DOB",_dob);
                                intent.putExtra("PhoneNo",_phoneNo);
                                intent.putExtra("Gender",_gender);
                                intent.putExtra("Email",email);
                                intent.putExtra("Password",systemPassword);
                                startActivity(intent);

                            }
                            else{
//                                Toast.makeText(Login_first_screen.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                                pass.setError("Wrong Password");
                                pass.requestFocus();
                            }
                        }
                        else{
//                            Toast.makeText(Login_first_screen.this, "No such user exist!", Toast.LENGTH_SHORT).show();
                            phone.setError("No such User exists");
                            phone.requestFocus();
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

//    public void back2(View view){
//        Intent intent = new Intent(Login_first_screen.this,Startup_for_login_signup.class);
//        startActivity(intent);
//        finish();
//    }

    private Boolean validatePhoneNo(){
        String val = phone.getEditText().getText().toString();

        if(val.isEmpty()){
            phone.setError("Field cannot be empty");
            return false;
        }
        else{
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }
    }

//    private boolean validateEmail(){
//        String val = email.getEditText().getText().toString().trim();
//        String checkEmail = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";
//
//        if(val.isEmpty()){
//            email.setError("Field cannot be empty!");
//            return false;
//        }
//        else if(!val.matches(checkEmail)){
//            email.setError("Invalid Email!");
//            return false;
//        }
//        else{
//            email.setError(null);
//            email.setErrorEnabled(false);
//            return true;
//        }

//    }

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