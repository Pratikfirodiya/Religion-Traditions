package com.example.myapplication.AuthenticationActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;

import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;

public class GetOtpActivity extends AppCompatActivity {
    OtpTextView otpTextView;
    Button validateotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_otp);
        otpTextView = findViewById(R.id.otp_view);
        validateotp=findViewById(R.id.otpvalidate);
        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {
                // fired when user types something in the Otpbox
            }
            @Override
            public void onOTPComplete(String otp) {
                // fired when user has entered the OTP fully.
                Toast.makeText(GetOtpActivity.this, "The OTP is " + otp,  Toast.LENGTH_SHORT).show();
            }
        });
    }
}