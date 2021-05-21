package com.example.myapplication.AuthenticationActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.Models.UserModel;
import com.example.myapplication.R;
import com.example.myapplication.Religionselec;
import com.example.myapplication.Splashscreen;
import com.example.myapplication.utils.ProgressBarHandler;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    EditText email,password;
    Button login,loginwithnum;
    SharedPreferences pref,pref2;
    ProgressBar progressBar;;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
      //  pref = getApplicationContext().getSharedPreferences("Religionset", 0);
        pref=getApplicationContext().getSharedPreferences("credentials", 0);
        email=findViewById(R.id.userEmail_login);
        password=findViewById(R.id.userPassword_login);
        progressBar=findViewById(R.id.loginprogressbar);
//        email.setText(pref2.getString("email",""));
  //      password.setText(pref2.getString("password",""));
        login=findViewById(R.id.loginbutton);
        loginwithnum=findViewById(R.id.Loginwithnumber);
        login.setOnClickListener(this);
        loginwithnum.setOnClickListener(this);
    }
    private Boolean validateEmail() {
        String val =  email.getText().toString();
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

    private Boolean validatepassword() {
        String val =  password.getText().toString();
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
public  void hidekeyboard()
{
    InputMethodManager imm=(InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
}
    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginbutton) {
          hidekeyboard();
            SharedPreferences.Editor editor = pref.edit();
            String emailvalue=email.getText().toString();
            String passwordvalue=password.getText().toString();
            UserModel user=new UserModel();
            user.login(emailvalue,passwordvalue,getApplicationContext());
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(2000,true);
//            ProgressBarHandler barHandler= new ProgressBarHandler(getApplicationContext());

  //         barHandler.show();
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
  //                  barHandler.hide();
                    if (user.getStatus()==true) {
                        editor.putString("email", email.getText().toString());
                        editor.putString("password", password.getText().toString());
                        editor.putInt("id",user.getid());
                        editor.commit();
//                        if (pref.contains("Religion")) {
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);
//
//                        } else {
                            Intent intent = new Intent(LoginActivity.this, Religionselec.class);
                            startActivity(intent);


                    }
                    else {
                               Toast.makeText(getApplicationContext(),"worng credentials",Toast.LENGTH_SHORT).show();

                    }
                }
            },3000);


//            if (email.getText().toString().equals(pref2.getString("email", "")) && password.getText().toString().equals(pref2.getString("password", ""))) {
////                if (pref.contains("Religion")) {
////                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
////                    startActivity(intent);
////
////                } else {
//                    Intent intent = new Intent(LoginActivity.this, Religionselec.class);
//                    startActivity(intent);
//
//
//            }
        }
        if(v.getId() == R.id.Loginwithnumber)
        {
            Intent intent = new Intent(LoginActivity.this, MobileLoginActivity.class);
            startActivity(intent);
        }
    }
}