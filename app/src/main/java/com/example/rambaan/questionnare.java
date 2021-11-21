package com.example.rambaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class questionnare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnare);



    }
    public void click(View view){
        int id = view.getId();
        Intent intent = new Intent(questionnare.this,output.class);
        intent.putExtra("vid",id);
        startActivity(intent);

          /*if(view.getId() == R.id.imageView1){
              String place1 = "This Chaupai is in the context of Gauri worship of Sri Sita in Balkand. Gauriji gave blessings to Sri Sitaji.";
              String res1 = "Questioner's question is good, work will be proved.";
              Intent intent = new Intent(MainActivity.this,output.class);
              intent.putExtra("res",res1);
              intent.putExtra("place",place1);
              startActivity(intent);
              finish();
          }*/
    }
}