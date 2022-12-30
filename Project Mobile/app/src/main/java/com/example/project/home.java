package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class home extends AppCompatActivity {
    String menus[] = {
            "Nav",
            "Mod" ,
            "Loc" ,
            "Heu" ,
            "rch",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        CircleMenu circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#d5c0a5"), R.drawable.home,R.drawable.remove)
                .addSubMenu(Color.parseColor("#cdcdcd"),R.drawable.nav)
                .addSubMenu(Color.parseColor("#00aeef"),R.drawable.book)
                .addSubMenu(Color.parseColor("#eca019"),R.drawable.map)
                .addSubMenu(Color.parseColor("#b4b4b4"),R.drawable.hour)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {



                        switch(index){
                            case 0: Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com.kh"));
                                startActivity(browserIntent);break;
                            case 1: Intent livreintent = new Intent(home.this, libraryy.class);
                                startActivity(livreintent);break;
                            case 2: Intent intent = new Intent(home.this,Googlemaps.class);
                                startActivity(intent);break;
                            case 3: Intent clockintent = new Intent(home.this,clock.class);
                                startActivity(clockintent);break;
//
                        }



                    }
                });
    }
}