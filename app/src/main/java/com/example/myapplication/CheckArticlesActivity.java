package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.Api.ValidateArticles;
import com.example.myapplication.AuthenticationActivities.LoginActivity;
import com.google.android.material.progressindicator.CircularProgressIndicator;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.myapplication.utils.ProgressBarHandler.show;

public class CheckArticlesActivity extends AppCompatActivity implements View.OnClickListener {

    TextView step1, step2, step3, step4, desc;
    ImageView imgurl;
    ProgressBar circularProgressIndicator;
    String steps, imageurl, descrption;
    int Articleid = 0;
    int i = 3000;
    Boolean status;
    Button approve, reject;
    TextView toolbartitle, typename;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_articles);
        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        step3 = findViewById(R.id.step3);
        step4 = findViewById(R.id.step4);
        desc = findViewById(R.id.desc);
        imgurl = findViewById(R.id.imgurl);
        approve = findViewById(R.id.approve);
        reject = findViewById(R.id.reject);
        circularProgressIndicator = findViewById(R.id.progressindicator);
        toolbartitle = findViewById(R.id.toolbar_title);
        typename = findViewById(R.id.typename_check);
        approve.setOnClickListener(this);
        reject.setOnClickListener(this);
        Articleid = Integer.parseInt(getIntent().getStringExtra("articleid"));
        loaddata(Articleid);

    }

    public void loaddata(int id) {
        sharedPreferences = getApplicationContext().getSharedPreferences("allArticles", 0);
        String value = sharedPreferences.getString("article", "");

        try {
            JSONObject jsonObject = new JSONObject(value);
            JSONArray jsonArray = jsonObject.getJSONArray("Articles");

            int n = jsonArray.length();
            for (int i = 0; i < n; i++) {
                JSONObject JObject = jsonArray.getJSONObject(i);
                if (JObject.get("article_id").equals(id)) {
                    descrption = JObject.get("desc").toString();
                    steps = JObject.get("procedure").toString();
                    imageurl = JObject.get("image_url").toString();
                    toolbartitle.setText(JObject.get("religion_name").toString());
                    typename.setText(JObject.get("type_name").toString());
                    Glide.with(getApplicationContext())
                            .load(imageurl)
                            .into(imgurl);
                    desc.setText(descrption);
                    String stepss[] = steps.split(";");
                    step1.setText(stepss[0]);
                    step2.setText(stepss[1]);
                    step3.setText(stepss[2]);
                    step4.setText(stepss[3]);


                }
            }
        } catch (JSONException ignored) {
            Toast.makeText(getApplicationContext(), "hello1" + ignored, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

        //   circularProgressIndicator.setProgress(100);
        if (v.getId() == R.id.approve) {

            circularProgressIndicator.setVisibility(View.VISIBLE);
            circularProgressIndicator.setProgress(2000);
            ValidateArticles validateArticles = new ValidateArticles(getApplicationContext());
            validateArticles.checkedarticles(Articleid, "Approved");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    status = validateArticles.getSuccess();
                    if (validateArticles.getSuccess()!=null) {
                        circularProgressIndicator.setVisibility(View.INVISIBLE);
                        showdialoag();

                    } else {
                        i = i + 1000;


                    }
                }
            }, i);


        } else {
            circularProgressIndicator.setVisibility(View.VISIBLE);
            circularProgressIndicator.setProgress(2000);
            ValidateArticles validateArticles = new ValidateArticles(getApplicationContext());
            validateArticles.checkedarticles(Articleid, "Rejected");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    status = validateArticles.getSuccess();
                    if (validateArticles.getSuccess()) {
                        circularProgressIndicator.setVisibility(View.INVISIBLE);
                        showdialoag();

                    } else {
                        i = i + 1000;


                    }
                }
            }, i);

        }
    }

    @SuppressLint("NewApi")
    public void showdialoag() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CheckArticlesActivity.this);
        if (status) {
            builder.setTitle("Status Updated");
            builder.setMessage("Go to Pending Articles");
            builder.setIcon(R.drawable.download);
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(CheckArticlesActivity.this, ValidateArticlesActivity.class);
                    startActivity(intent);

                }
            });


            AlertDialog dialog = builder.create();
            // Display the alert dialog on interface
            dialog.show();

        } else {
            builder.setTitle("Status Cannot  Updated");
            builder.setMessage("retry");
            builder.setIcon(R.drawable.crosssin);
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(CheckArticlesActivity.this, ValidateArticlesActivity.class);
                    startActivity(intent);

                }
            });


            AlertDialog dialog = builder.create();
            // Display the alert dialog on interface
            dialog.show();
        }


        // Set a title for alert dialog


        // Ask the final question


    }
}
