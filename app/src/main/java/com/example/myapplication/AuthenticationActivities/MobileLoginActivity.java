package com.example.myapplication.AuthenticationActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class MobileLoginActivity extends AppCompatActivity implements View.OnClickListener {
    String CountryZipCode,CountryID ;
    Spinner spinner;
    TextView textView;
    Button getotp;
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_login);
         getotp=findViewById(R.id.otpbutton);
        spinner = findViewById(R.id.conunty_spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.CountryCodes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        getotp.setOnClickListener(this);
        TelephonyManager manager = (TelephonyManager) MobileLoginActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
        CountryID = manager.getSimCountryIso().toUpperCase();
        String[] rl = this.getResources().getStringArray(R.array.CountryCodes);
        for (int i = 0; i < rl.length; i++) {
            String[] g = rl[i].split(",");
            if (g[1].trim().equals(CountryID.trim())) {
                CountryZipCode = g[0];
                break;
            }
        }
             getCountryZipcode();
        Toast.makeText(MobileLoginActivity.this, CountryZipCode, Toast.LENGTH_LONG).show();


    }

public void getCountryZipcode() {

        int index = 0;
        String[] rl = this.getResources().getStringArray(R.array.CountryCodes);
        for (int i = 0; i < rl.length; i++) {
            String[] g = rl[i].split(",");
            if (g[0].equals(CountryZipCode)) {
                index = i;
                Toast.makeText(MobileLoginActivity.this, "" + spinner.getItemAtPosition(i), Toast.LENGTH_LONG).show();

                spinner.setSelection(i);
            }
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent =new Intent(MobileLoginActivity.this,GetOtpActivity.class);
        startActivity(intent);
    }
}

