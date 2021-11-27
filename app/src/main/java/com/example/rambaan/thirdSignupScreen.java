package com.example.rambaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class thirdSignupScreen extends AppCompatActivity {

    ImageView backBtn;
    Button phnNext;
    CountryCodePicker countryCodePicker;
    TextInputLayout phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_signup_screen);

        phnNext  = findViewById(R.id.phn_no_next);
        backBtn = findViewById(R.id.third_activity_back_btn);
        countryCodePicker = findViewById(R.id.cuntry_code_picker);
        phoneNo = findViewById(R.id.phn_no_for_otp);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thirdSignupScreen.this,nextSignupScreen.class);
                startActivity(intent);
            }
        });

        phnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = getIntent().getExtras();

                String _fullname = b.getString("Name");
                String _email = b.getString("Email");
                String _username = b.getString("Username");
                String _password = b.getString("Password");
                String _Dob = b.getString("DOB");
                String _gender = b.getString("Gender");

                String getUserEnteredPhoneNumber = phoneNo.getEditText().getText().toString().trim();
                String _phoneNo = "+"+countryCodePicker.getFullNumber()+getUserEnteredPhoneNumber;

                Intent intent = new Intent(thirdSignupScreen.this,VerifyOTP.class);
                intent.putExtra("Name",_fullname);
                intent.putExtra("Email",_email);
                intent.putExtra("Username",_username);
                intent.putExtra("Password",_password);
                intent.putExtra("DOB",_Dob);
                intent.putExtra("Gender",_gender);
                intent.putExtra("phoneNo",_phoneNo);
                startActivity(intent);
            }
        });

    }
}