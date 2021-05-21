package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences pref;
    ListView settinglv;
    Button settingedit;
    String[] religion={"ss","c3c"};
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        pref = getApplicationContext().getSharedPreferences("Religionset", 0);
        settinglv = findViewById(R.id.Settinglistview);
        settingedit=findViewById(R.id.Settingeditbutton);
        getSupportActionBar().setTitle("Settings");
        Set<String> fetch =   pref.getStringSet("Religion",null);
        List<String> list = new ArrayList<String>(fetch);
        settingedit.setOnClickListener(this);
//
       arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,list );
        settinglv.setAdapter(arrayAdapter);
        for(int i = 0 ; i < list.size() ; i++){

               settinglv.setItemChecked(i,true);

        }
        settinglv.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settingmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Button b=(Button) findViewById(R.id.edit);
        Intent intent=new Intent(SettingsActivity.this,Religionselec.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(SettingsActivity.this,Religionselec.class);
        startActivity(intent);
    }
}