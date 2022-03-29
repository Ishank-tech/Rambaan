package com.example.rambaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
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

        countryCodePicker = findViewById(R.id.cuntry_code_picker);
        phoneNo = (TextInputLayout) findViewById(R.id.phn_no_for_otp);
        phnNext  = findViewById(R.id.phn_no_next);
        backBtn = findViewById(R.id.third_activity_back_btn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thirdSignupScreen.this,nextSignupScreen.class);
                startActivity(intent);
                finish();
            }
        });

        phnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validatePhoneNo()){
                    return;
                }

                String _FullName = getIntent().getStringExtra("Name");
                String _EMail = getIntent().getStringExtra("Email");
                String _USername = getIntent().getStringExtra("Username");
                String _PAssword = getIntent().getStringExtra("Password");
                String _Dob = getIntent().getStringExtra("DOB");
                String _Gender = getIntent().getStringExtra("Gender");

                //String _fullname = b.getString("Name");
                //String _email = b.getString("Email");
                //String _username = b.getString("Username");
                //String _password = b.getString("Password");
                //String _Dob = b.getString("DOB");
                //String _gender = b.getString("Gender");
                String getUserEnteredPhoneNumber = phoneNo.getEditText().getText().toString().trim();
                String _phoneNo = "+"+countryCodePicker.getSelectedCountryCode()+getUserEnteredPhoneNumber;

                Intent i = new Intent(getApplicationContext(),VerifyOTP.class);
                i.putExtra("Name",_FullName);
                i.putExtra("Email",_EMail);
                i.putExtra("Username",_USername);
                i.putExtra("Password",_PAssword);
                i.putExtra("DOB",_Dob);
                i.putExtra("Gender",_Gender);
                i.putExtra("PhoneNo",_phoneNo);
                startActivity(i);
                finish();
            }
        });

    }

    private Boolean validatePhoneNo(){
        String val = phoneNo.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            phoneNo.setError("Field cannot be empty");
            return false;
        }
        else{
            phoneNo.setError(null);
            phoneNo.setErrorEnabled(false);
            return true;
        }
    }

}