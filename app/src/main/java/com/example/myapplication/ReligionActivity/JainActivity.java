package com.example.myapplication.ReligionActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.fragment.Festivalfrag;
import com.example.myapplication.fragment.Occasion;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class JainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jain);
        getSupportActionBar().setTitle(getIntent().getStringExtra("tag"));
//        MaterialButtonToggleGroup materialButtonToggleGroup = findViewById(R.id.JaintoggleGroup);
//        if ( materialButtonToggleGroup.getCheckedButtonId()== R.id.btnfestival) {
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.Jainframe,new Festivalfrag()).commit();
//        }
//        materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
//            @Override
//            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
//                if (isChecked) {
//                    if (checkedId == R.id.btnfestival) {
//                        Toast.makeText(JainActivity.this,"ok",Toast.LENGTH_LONG).show();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.Jainframe,new Festivalfrag()).commit();
//                    }
//                    if (checkedId == R.id.btnOccasion) {
//
//                        Toast.makeText(JainActivity.this,"ook",Toast.LENGTH_LONG).show();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.Jainframe,new Occasion()).commit();
//                    }
//                }
//            }
//        });
    }
}