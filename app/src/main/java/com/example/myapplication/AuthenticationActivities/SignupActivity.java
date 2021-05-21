package com.example.myapplication.AuthenticationActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.UserModel;
import com.example.myapplication.R;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    EditText firstname, lastname, email, phonenumber, password;
    Button registerbutton, alreadyuser;
    CheckBox termcondition;
    SharedPreferences pref;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Registration");
        pref = getApplicationContext().getSharedPreferences("credentials", 0); //
        firstname = findViewById(R.id.userFirstname);
        lastname = findViewById(R.id.userlastName);
        email = findViewById(R.id.userEmail);
        phonenumber = findViewById(R.id.userNumber);
        password = findViewById(R.id.userPassword);
        registerbutton = findViewById(R.id.registerbutton);
        termcondition = findViewById(R.id.termcondition);
        alreadyuser = findViewById(R.id.alreadyuserbutton);
        alreadyuser.setOnClickListener(this);
        registerbutton.setOnClickListener(this);
    }

    private Boolean validatePhoneNo() {
        String val = phonenumber.getText().toString();

        if (val.isEmpty()) {
            phonenumber.setError("Field cannot be empty");
            return false;
        } else {
            phonenumber.setError(null);

            return true;
        }
    }

    private Boolean validatepassword() {
        String val = password.getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            password.setError("password is too weak");
            return false;
        } else {
            password.setError(null);

            return true;
        }
    }

    private Boolean validateName() {
        String val = firstname.getText().toString();

        if (val.isEmpty()) {
            firstname.setError("Field cannot be empty");
            return false;
        } else {
            firstname.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);

            return true;
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.loginbutton) {
            SharedPreferences.Editor editor = pref.edit();
            String emailvalue = email.getText().toString();
            String passwordvalue = password.getText().toString();
            String firstnamevalue = firstname.getText().toString();
            String lastnamevalue = lastname.getText().toString();
            UserModel user = new UserModel();


            if (validateName() | validatepassword() | validatePhoneNo() | validateEmail()) {
                if (termcondition.isChecked()) {
                    user.register(firstnamevalue, lastnamevalue, emailvalue, passwordvalue, getApplicationContext());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (user.getStatus()) {

                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "" + user.getStatus(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignupActivity.this, "User already exists", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, 2000);

                } else {
                    Toast.makeText(SignupActivity.this, "accept term and condition", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(SignupActivity.this, "Enter Correctly", Toast.LENGTH_LONG).show();
            }

        }
        if (v.getId() == R.id.alreadyuserbutton) {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }
    //   private Boolean validateUsername() {
//        String val = regUsername.getEditText().getText().toString();
//        String noWhiteSpace = "\\A\\w{4,20}\\z";
//
//        if (val.isEmpty()) {
//            regUsername.setError("Field cannot be empty");
//            return false;
//        } else if (val.length() >= 15) {
//            regUsername.setError("Username too long");
//            return false;
//        } else if (!val.matches(noWhiteSpace)) {
//            regUsername.setError("White Spaces are not allowed");
//            return false;
//        } else {
//            regUsername.setError(null);
//            regUsername.setErrorEnabled(false);
//            return true;
//        }
//    }
}