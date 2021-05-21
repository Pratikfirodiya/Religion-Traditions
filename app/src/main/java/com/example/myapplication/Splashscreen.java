package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.myapplication.Api.GetData;
import com.example.myapplication.AuthenticationActivities.LoginActivity;
import com.example.myapplication.AuthenticationActivities.SignupActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.widget.BaseAdapter;

public class Splashscreen extends AppCompatActivity {
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        pref = getApplicationContext().getSharedPreferences("credentials", 0);
   Handler     handler=new Handler();
        GetData getData=new GetData(getApplicationContext());
        getData.execute();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(Splashscreen.this, Religionselec.class);
                startActivity(intent);
                finish();
//                if(pref.contains("email")&&pref.contains("password"))
//                {
//                    Intent intent=new Intent(Splashscreen.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                else {
//                    Intent intent = new Intent(Splashscreen.this, SignupActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
            }
        },3000);
    }
}