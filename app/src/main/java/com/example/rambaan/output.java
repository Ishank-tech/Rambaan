package com.example.rambaan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class output extends AppCompatActivity {
    private TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        res = (TextView) findViewById(R.id.res);

        int viewId = getIntent().getIntExtra("vid",0);

        if(viewId == R.id.imageView1 ||  viewId == R.id.imageView10 || viewId == R.id.imageView19 || viewId == R.id.imageView28 || viewId == R.id.imageView37
        || viewId == R.id.imageView46 || viewId == R.id.imageView55 || viewId == R.id.imageView64 || viewId == R.id.imageView73 || viewId == R.id.imageView82
        || viewId == R.id.imageView91 || viewId == R.id.imageView100 || viewId == R.id.imageView109 || viewId == R.id.imageView118 || viewId == R.id.imageView127
        || viewId == R.id.imageView136 || viewId == R.id.imageView145 || viewId == R.id.imageView154 || viewId == R.id.imageView163 || viewId == R.id.imageView172
        || viewId == R.id.imageView181 || viewId == R.id.imageView190 || viewId == R.id.imageView199 || viewId == R.id.imageView208 || viewId == R.id.imageView217){
            //String place1 = "This Chaupai is in the context of Gauri worship of Sri Sita in Balkand. Gauriji gave blessings to Sri Sitaji.";
            String res1 = "Questioner's question is good, work will be proved.";
            //place.setText(place1);
            res.setText(res1);
        }else if(viewId == R.id.imageView2 || viewId == R.id.imageView11 || viewId == R.id.imageView20 || viewId == R.id.imageView29 || viewId == R.id.imageView38
        || viewId == R.id.imageView47 || viewId == R.id.imageView56 || viewId == R.id.imageView65 || viewId == R.id.imageView74 || viewId == R.id.imageView83
        || viewId == R.id.imageView92 || viewId == R.id.imageView101 || viewId == R.id.imageView110 || viewId == R.id.imageView119 || viewId == R.id.imageView128
        || viewId == R.id.imageView137 || viewId == R.id.imageView146 || viewId == R.id.imageView155 || viewId == R.id.imageView164 || viewId == R.id.imageView173
        || viewId == R.id.imageView182 || viewId == R.id.imageView191 || viewId == R.id.imageView200 || viewId == R.id.imageView209 || viewId == R.id.imageView218){
            String res1 = "Start your work by remembering God, you will get success.";
            res.setText(res1);
        }else if(viewId == R.id.imageView3 || viewId == R.id.imageView12 || viewId == R.id.imageView21 || viewId == R.id.imageView30 || viewId == R.id.imageView39
                || viewId == R.id.imageView48 || viewId == R.id.imageView57 || viewId == R.id.imageView66 || viewId == R.id.imageView75 || viewId == R.id.imageView84
                || viewId == R.id.imageView93 || viewId == R.id.imageView102 || viewId == R.id.imageView111 || viewId == R.id.imageView120 || viewId == R.id.imageView129
                || viewId == R.id.imageView138 || viewId == R.id.imageView147 || viewId == R.id.imageView156 || viewId == R.id.imageView165 || viewId == R.id.imageView174
                || viewId == R.id.imageView183 || viewId == R.id.imageView192 || viewId == R.id.imageView201 || viewId == R.id.imageView210 || viewId == R.id.imageView219){
            String res1 = "There is no good in this work. There is doubt in the success of the work.";
            res.setText(res1);
        }else if(viewId == R.id.imageView4 || viewId == R.id.imageView13 || viewId == R.id.imageView22 || viewId == R.id.imageView31 || viewId == R.id.imageView40
                || viewId == R.id.imageView49 || viewId == R.id.imageView58 || viewId == R.id.imageView67 || viewId == R.id.imageView76 || viewId == R.id.imageView85
                || viewId == R.id.imageView94 || viewId == R.id.imageView103 || viewId == R.id.imageView112 || viewId == R.id.imageView121 || viewId == R.id.imageView130
                || viewId == R.id.imageView139 || viewId == R.id.imageView148 || viewId == R.id.imageView157 || viewId == R.id.imageView166 || viewId == R.id.imageView175
                || viewId == R.id.imageView184 || viewId == R.id.imageView193 || viewId == R.id.imageView202 || viewId == R.id.imageView211 || viewId == R.id.imageView220){
            String res1 = "Leave the company of false people. There is doubt about the completion of the work.";
            res.setText(res1);
        }else if(viewId == R.id.imageView5 || viewId == R.id.imageView14 || viewId == R.id.imageView23 || viewId == R.id.imageView32 || viewId == R.id.imageView41
                || viewId == R.id.imageView50 || viewId == R.id.imageView59 || viewId == R.id.imageView68 || viewId == R.id.imageView77 || viewId == R.id.imageView86
                || viewId == R.id.imageView95 || viewId == R.id.imageView104 || viewId == R.id.imageView113 || viewId == R.id.imageView122 || viewId == R.id.imageView131
                || viewId == R.id.imageView140 || viewId == R.id.imageView149 || viewId == R.id.imageView158 || viewId == R.id.imageView167 || viewId == R.id.imageView176
                || viewId == R.id.imageView185 || viewId == R.id.imageView194 || viewId == R.id.imageView203 || viewId == R.id.imageView212 || viewId == R.id.imageView221){
            String res1 = "There is doubt about the work being done, so it is better to leave it to the Divine.";
            res.setText(res1);
        }else if(viewId == R.id.imageView6 || viewId == R.id.imageView15 || viewId == R.id.imageView24 || viewId == R.id.imageView33 || viewId == R.id.imageView42
                || viewId == R.id.imageView51 || viewId == R.id.imageView60 || viewId == R.id.imageView69 || viewId == R.id.imageView78 || viewId == R.id.imageView87
                || viewId == R.id.imageView96 || viewId == R.id.imageView105 || viewId == R.id.imageView114 || viewId == R.id.imageView123 || viewId == R.id.imageView132
                || viewId == R.id.imageView141 || viewId == R.id.imageView150 || viewId == R.id.imageView159 || viewId == R.id.imageView168 || viewId == R.id.imageView177
                || viewId == R.id.imageView186 || viewId == R.id.imageView195 || viewId == R.id.imageView204 || viewId == R.id.imageView213 || viewId == R.id.imageView222){
            String res1 = "The Question is good. The work will be done.";
            res.setText(res1);
        }else if(viewId == R.id.imageView7 || viewId == R.id.imageView16 || viewId == R.id.imageView25 || viewId == R.id.imageView34 || viewId == R.id.imageView43
                || viewId == R.id.imageView52 || viewId == R.id.imageView61 || viewId == R.id.imageView70 || viewId == R.id.imageView79 || viewId == R.id.imageView88
                || viewId == R.id.imageView97 || viewId == R.id.imageView106 || viewId == R.id.imageView115 || viewId == R.id.imageView124 || viewId == R.id.imageView133
                || viewId == R.id.imageView142 || viewId == R.id.imageView151 || viewId == R.id.imageView160 || viewId == R.id.imageView169 || viewId == R.id.imageView178
                || viewId == R.id.imageView187 || viewId == R.id.imageView196 || viewId == R.id.imageView205 || viewId == R.id.imageView214 || viewId == R.id.imageView223){
            String res1 = "The question is great. The work will be successful.";
            res.setText(res1);
        }else if(viewId == R.id.imageView8 || viewId == R.id.imageView17 || viewId == R.id.imageView26 || viewId == R.id.imageView35 || viewId == R.id.imageView44 || viewId == R.id.imageView53
                || viewId == R.id.imageView62 || viewId == R.id.imageView71 || viewId == R.id.imageView80 || viewId == R.id.imageView89 || viewId == R.id.imageView98
                || viewId == R.id.imageView107 || viewId == R.id.imageView116 || viewId == R.id.imageView125 || viewId == R.id.imageView134 || viewId == R.id.imageView143
                || viewId == R.id.imageView152 || viewId == R.id.imageView161 || viewId == R.id.imageView170 || viewId == R.id.imageView179 || viewId == R.id.imageView188
                || viewId == R.id.imageView197 || viewId == R.id.imageView206 || viewId == R.id.imageView215 || viewId == R.id.imageView224){
            String res1 = "There is doubt about the completion of the work.";
            res.setText(res1);
        }else{
            String res1 = "The question is very good. The work will be done.";
            res.setText(res1);
        }

    }
}