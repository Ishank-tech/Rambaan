package com.example.rambaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class nextSignupScreen extends AppCompatActivity {

    ImageView back;
    Button go;
    RadioGroup radioGroup;
    RadioButton selectedGender;
    DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_signup_screen);

        radioGroup = findViewById(R.id.radiogroup);
        datePicker = findViewById(R.id.age_picker);
        back = findViewById(R.id.back_btn);
        go = findViewById(R.id.next);

    }
    public void back(View view){
        Intent intent = new Intent(nextSignupScreen.this, Signup_first_screen.class);
        startActivity(intent);
    }

    public void Goto(View view){
        if(!validateGender() | !validateAge()){
            return;
        }

        String _fullName = getIntent().getStringExtra("Name");
        String _Email = getIntent().getStringExtra("Email");
        String _Username = getIntent().getStringExtra("Username");
        String _Password = getIntent().getStringExtra("Password");

        selectedGender = findViewById(radioGroup.getCheckedRadioButtonId());
        String _gender = selectedGender.getText().toString();

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        String date = day+"/"+month+"/"+year;

        Intent intent = new Intent(nextSignupScreen.this, thirdSignupScreen.class);
        intent.putExtra("Gender",_gender);
        intent.putExtra("DOB",date);
        intent.putExtra("Name",_fullName);
        intent.putExtra("Email",_Email);
        intent.putExtra("Username",_Username);
        intent.putExtra("Password",_Password);
        /*Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            intent.putExtras(bundle);
        }*/
        startActivity(intent);
    }

    private Boolean validateGender(){
        if(radioGroup.getCheckedRadioButtonId()==-1){
            Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private boolean validateAge(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid = currentYear - userAge;

        if(isAgeValid<12){
            Toast.makeText(this, "You are not Eligible to apply", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;

    }

}