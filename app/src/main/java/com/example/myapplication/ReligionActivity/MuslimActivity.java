package com.example.myapplication.ReligionActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.fragment.Festivalfrag;
import com.example.myapplication.fragment.Occasion;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class MuslimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muslim);
        getSupportActionBar().setTitle(getIntent().getStringExtra("tag"));
        MaterialButtonToggleGroup materialButtonToggleGroup = findViewById(R.id.MuslimtoggleGroup);
        if ( materialButtonToggleGroup.getCheckedButtonId()== R.id.Muslimbtnfestival) {

            getSupportFragmentManager().beginTransaction().replace(R.id.Muslimframe,new Festivalfrag()).commit();
        }
        materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    if (checkedId == R.id.Muslimbtnfestival) {
                        Toast.makeText(MuslimActivity.this,"ok",Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.Muslimframe,new Festivalfrag()).commit();
                    }
                    if (checkedId == R.id.MuslimbtnOccasion) {

                        Toast.makeText(MuslimActivity.this,"ook",Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.Muslimframe,new Occasion()).commit();
                    }
                }
            }
        });
    }
}