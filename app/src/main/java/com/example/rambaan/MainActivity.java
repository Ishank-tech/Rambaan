package com.example.rambaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.ar.core.AugmentedFace;
import com.google.ar.core.Frame;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.Texture;
import com.google.ar.sceneform.ux.AugmentedFaceNode;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //public ImageView [] a = new ImageView[225];

//    private ModelRenderable modelRenderable;
//    private Texture texture;
//    private boolean isAdded = false;
//    private final HashMap<AugmentedFace, AugmentedFaceNode> faceNodeMap = new HashMap<AugmentedFace, AugmentedFaceNode>();

    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//        CustomArFragment customArFragment = (CustomArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_grid_on_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_music_note_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_account_circle_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch(item.getId()){
                    case 1:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        break;
                    case 2:
                        fragment = new QuestionnareFragment();
//                        Intent intent = new Intent(MainActivity.this,questionnare.class);
//                        startActivity(intent);
                        loadFragment(fragment);
                        break;
                    case 3:
//                        fragment = new MusicFragment();
                        Intent intent = new Intent(MainActivity.this,SnapActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        fragment = new AccountFragment();
                        loadFragment(fragment);
                        break;
                }

            }
        });

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch(item.getId()){
                    case 1:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        break;
                    case 2:
                        fragment = new QuestionnareFragment();
//                        Intent intent = new Intent(MainActivity.this,questionnare.class);
//                        startActivity(intent);
                        loadFragment(fragment);
                        break;
                    case 3:
//                        fragment = new MusicFragment();
                        Intent intent = new Intent(MainActivity.this,SnapActivity.class);
                        startActivity(intent);

                        break;
                    case 4:
                        fragment = new AccountFragment();
                        loadFragment(fragment);
                        break;
                }

            }
        });

        bottomNavigation.show(1,true);


            /*a[0]=findViewById(R.id.imageView1);
            a[1]=findViewById(R.id.imageView2);
            a[2]=findViewById(R.id.imageView3);
            a[3]=findViewById(R.id.imageView4);
            a[4]=findViewById(R.id.imageView5);
            a[5]=findViewById(R.id.imageView6);
            a[6]=findViewById(R.id.imageView7);
            a[7]=findViewById(R.id.imageView8);
            a[8]=findViewById(R.id.imageView9);
            a[9]=findViewById(R.id.imageView10);
            a[10]=findViewById(R.id.imageView11);
            a[11]=findViewById(R.id.imageView12);
            a[12]=findViewById(R.id.imageView13);
            a[13]=findViewById(R.id.imageView14);
            a[14]=findViewById(R.id.imageView15);
            a[15]=findViewById(R.id.imageView16);
            a[16]=findViewById(R.id.imageView17);
            a[17]=findViewById(R.id.imageView18);
            a[18]=findViewById(R.id.imageView19);
            a[19]=findViewById(R.id.imageView20);
            a[20]=findViewById(R.id.imageView21);
            a[21]=findViewById(R.id.imageView22);
            a[22]=findViewById(R.id.imageView23);
            a[23]=findViewById(R.id.imageView24);
            a[24]=findViewById(R.id.imageView25);
            a[25]=findViewById(R.id.imageView26);
            a[26]=findViewById(R.id.imageView27);
            a[27]=findViewById(R.id.imageView28);
            a[28]=findViewById(R.id.imageView29);
            a[29]=findViewById(R.id.imageView30);
            a[30]=findViewById(R.id.imageView31);
            a[31]=findViewById(R.id.imageView32);
            a[32]=findViewById(R.id.imageView33);
            a[33]=findViewById(R.id.imageView34);
            a[34]=findViewById(R.id.imageView35);
            a[35]=findViewById(R.id.imageView36);
            a[36]=findViewById(R.id.imageView37);
            a[37]=findViewById(R.id.imageView38);
            a[38]=findViewById(R.id.imageView39);
            a[39]=findViewById(R.id.imageView40);
            a[40]=findViewById(R.id.imageView41);
            a[41]=findViewById(R.id.imageView42);
            a[42]=findViewById(R.id.imageView43);
            a[43]=findViewById(R.id.imageView44);
            a[44]=findViewById(R.id.imageView45);
            a[45]=findViewById(R.id.imageView46);
            a[46]=findViewById(R.id.imageView47);
            a[47]=findViewById(R.id.imageView48);
            a[48]=findViewById(R.id.imageView49);
            a[49]=findViewById(R.id.imageView50);
            a[50]=findViewById(R.id.imageView51);
            a[51]=findViewById(R.id.imageView52);
            a[52]=findViewById(R.id.imageView53);
            a[53]=findViewById(R.id.imageView54);
            a[54]=findViewById(R.id.imageView55);
            a[55]=findViewById(R.id.imageView56);
            a[56]=findViewById(R.id.imageView57);
            a[57]=findViewById(R.id.imageView58);
            a[58]=findViewById(R.id.imageView59);
            a[59]=findViewById(R.id.imageView60);
            a[60]=findViewById(R.id.imageView61);
            a[61]=findViewById(R.id.imageView62);
            a[62]=findViewById(R.id.imageView63);
            a[63]=findViewById(R.id.imageView64);
            a[64]=findViewById(R.id.imageView65);
            a[65]=findViewById(R.id.imageView66);
            a[66]=findViewById(R.id.imageView67);
            a[67]=findViewById(R.id.imageView68);
            a[68]=findViewById(R.id.imageView69);
            a[69]=findViewById(R.id.imageView70);
            a[70]=findViewById(R.id.imageView71);
            a[71]=findViewById(R.id.imageView72);
            a[72]=findViewById(R.id.imageView73);
            a[73]=findViewById(R.id.imageView74);
            a[74]=findViewById(R.id.imageView75);
            a[75]=findViewById(R.id.imageView76);
            a[76]=findViewById(R.id.imageView77);
            a[77]=findViewById(R.id.imageView78);
            a[78]=findViewById(R.id.imageView79);
            a[79]=findViewById(R.id.imageView80);
            a[80]=findViewById(R.id.imageView81);
            a[81]=findViewById(R.id.imageView82);
            a[82]=findViewById(R.id.imageView83);
            a[83]=findViewById(R.id.imageView84);
            a[84]=findViewById(R.id.imageView85);
            a[85]=findViewById(R.id.imageView86);
            a[86]=findViewById(R.id.imageView87);
            a[87]=findViewById(R.id.imageView88);
            a[88]=findViewById(R.id.imageView89);
            a[89]=findViewById(R.id.imageView90);
            a[90]=findViewById(R.id.imageView91);
            a[91]=findViewById(R.id.imageView92);
            a[92]=findViewById(R.id.imageView93);
            a[93]=findViewById(R.id.imageView94);
            a[94]=findViewById(R.id.imageView95);
            a[95]=findViewById(R.id.imageView96);
            a[96]=findViewById(R.id.imageView97);
            a[97]=findViewById(R.id.imageView98);
            a[98]=findViewById(R.id.imageView99);
            a[99]=findViewById(R.id.imageView100);
            a[100]=findViewById(R.id.imageView101);
            a[101]=findViewById(R.id.imageView102);
            a[102]=findViewById(R.id.imageView103);
            a[103]=findViewById(R.id.imageView104);
            a[104]=findViewById(R.id.imageView105);
            a[105]=findViewById(R.id.imageView106);
            a[106]=findViewById(R.id.imageView107);
            a[107]=findViewById(R.id.imageView108);
            a[108]=findViewById(R.id.imageView109);
            a[109]=findViewById(R.id.imageView110);
        a[110]=findViewById(R.id.imageView111);
        a[111]=findViewById(R.id.imageView112);
        a[112]=findViewById(R.id.imageView113);
        a[113]=findViewById(R.id.imageView114);
        a[114]=findViewById(R.id.imageView115);
        a[115]=findViewById(R.id.imageView116);
        a[116]=findViewById(R.id.imageView117);
        a[117]=findViewById(R.id.imageView118);
        a[118]=findViewById(R.id.imageView119);
        a[119]=findViewById(R.id.imageView120);
        a[120]=findViewById(R.id.imageView121);
        a[121]=findViewById(R.id.imageView122);
        a[122]=findViewById(R.id.imageView123);
        a[123]=findViewById(R.id.imageView124);
        a[124]=findViewById(R.id.imageView125);
        a[125]=findViewById(R.id.imageView126);
        a[126]=findViewById(R.id.imageView127);
        a[127]=findViewById(R.id.imageView128);
        a[128]=findViewById(R.id.imageView129);
        a[129]=findViewById(R.id.imageView130);
        a[130]=findViewById(R.id.imageView131);
        a[131]=findViewById(R.id.imageView132);
        a[132]=findViewById(R.id.imageView133);
        a[133]=findViewById(R.id.imageView134);
        a[134]=findViewById(R.id.imageView135);
        a[135]=findViewById(R.id.imageView136);
        a[136]=findViewById(R.id.imageView137);
        a[137]=findViewById(R.id.imageView138);
        a[138]=findViewById(R.id.imageView139);
        a[139]=findViewById(R.id.imageView140);
        a[140]=findViewById(R.id.imageView141);
        a[141]=findViewById(R.id.imageView142);
        a[142]=findViewById(R.id.imageView143);
        a[143]=findViewById(R.id.imageView144);
        a[144]=findViewById(R.id.imageView145);
        a[145]=findViewById(R.id.imageView146);
        a[146]=findViewById(R.id.imageView147);
        a[147]=findViewById(R.id.imageView148);
        a[148]=findViewById(R.id.imageView149);
        a[149]=findViewById(R.id.imageView150);
        a[150]=findViewById(R.id.imageView151);
        a[151]=findViewById(R.id.imageView152);
        a[152]=findViewById(R.id.imageView153);
        a[153]=findViewById(R.id.imageView154);
        a[154]=findViewById(R.id.imageView155);
        a[155]=findViewById(R.id.imageView156);
        a[156]=findViewById(R.id.imageView157);
        a[157]=findViewById(R.id.imageView158);
        a[158]=findViewById(R.id.imageView159);
        a[159]=findViewById(R.id.imageView160);
        a[160]=findViewById(R.id.imageView161);
        a[161]=findViewById(R.id.imageView162);
        a[162]=findViewById(R.id.imageView163);
        a[163]=findViewById(R.id.imageView164);
        a[164]=findViewById(R.id.imageView165);
        a[165]=findViewById(R.id.imageView166);
        a[166]=findViewById(R.id.imageView167);
        a[167]=findViewById(R.id.imageView168);
        a[168]=findViewById(R.id.imageView169);
        a[169]=findViewById(R.id.imageView170);
        a[170]=findViewById(R.id.imageView171);
        a[171]=findViewById(R.id.imageView172);
        a[172]=findViewById(R.id.imageView173);
        a[173]=findViewById(R.id.imageView174);
        a[174]=findViewById(R.id.imageView175);
        a[175]=findViewById(R.id.imageView176);
        a[176]=findViewById(R.id.imageView177);
        a[177]=findViewById(R.id.imageView178);
        a[178]=findViewById(R.id.imageView179);
        a[179]=findViewById(R.id.imageView180);
        a[180]=findViewById(R.id.imageView181);
        a[181]=findViewById(R.id.imageView182);
        a[182]=findViewById(R.id.imageView183);
        a[183]=findViewById(R.id.imageView184);
        a[184]=findViewById(R.id.imageView185);
        a[185]=findViewById(R.id.imageView186);
        a[186]=findViewById(R.id.imageView187);
        a[187]=findViewById(R.id.imageView188);
        a[188]=findViewById(R.id.imageView189);
        a[189]=findViewById(R.id.imageView190);
        a[190]=findViewById(R.id.imageView191);
        a[191]=findViewById(R.id.imageView192);
        a[192]=findViewById(R.id.imageView193);
        a[193]=findViewById(R.id.imageView194);
        a[194]=findViewById(R.id.imageView195);
        a[195]=findViewById(R.id.imageView196);
        a[196]=findViewById(R.id.imageView197);
        a[197]=findViewById(R.id.imageView198);
        a[198]=findViewById(R.id.imageView199);
        a[199]=findViewById(R.id.imageView200);
        a[200]=findViewById(R.id.imageView201);
        a[201]=findViewById(R.id.imageView202);
        a[202]=findViewById(R.id.imageView203);
        a[203]=findViewById(R.id.imageView204);
        a[204]=findViewById(R.id.imageView205);
        a[205]=findViewById(R.id.imageView206);
        a[206]=findViewById(R.id.imageView207);
        a[207]=findViewById(R.id.imageView208);
        a[208]=findViewById(R.id.imageView209);
        a[209]=findViewById(R.id.imageView210);
        a[210]=findViewById(R.id.imageView211);
        a[211]=findViewById(R.id.imageView212);
        a[212]=findViewById(R.id.imageView213);
        a[213]=findViewById(R.id.imageView214);
        a[214]=findViewById(R.id.imageView215);
        a[215]=findViewById(R.id.imageView216);
        a[216]=findViewById(R.id.imageView217);
        a[217]=findViewById(R.id.imageView218);
        a[218]=findViewById(R.id.imageView219);
        a[219]=findViewById(R.id.imageView220);
        a[220]=findViewById(R.id.imageView221);
        a[221]=findViewById(R.id.imageView222);
        a[222]=findViewById(R.id.imageView223);
        a[223]=findViewById(R.id.imageView224);
        a[224]=findViewById(R.id.imageView225);*/

    }

    public void addAccount(View view){
        Intent intent = new Intent(MainActivity.this,Startup_for_login_signup.class);
        startActivity(intent);
        finish();
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.view,fragment).commit();

    }


}