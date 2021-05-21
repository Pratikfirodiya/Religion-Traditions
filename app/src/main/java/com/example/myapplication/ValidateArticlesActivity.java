package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.example.myapplication.Adapters.ValidationlistAdapter;
import com.example.myapplication.Api.ValidateArticles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ValidateArticlesActivity extends AppCompatActivity {

SkeletonScreen skeletonScreen;
    ListView listView;
   RelativeLayout relativeLayout;
    List<String> a = new ArrayList<>();
    List<String> b = new ArrayList<>();
    List<String> c = new ArrayList<>();
    List<String> Articleid = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_articles);
        listView = findViewById(R.id.validatelist);
        relativeLayout=findViewById(R.id.rlartciles);
        skeletonScreen = Skeleton.bind(relativeLayout)
                .load(R.layout.layout_placeholder)
                .show();
        TextView textView = new TextView(this);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setText("List of Articles");
        ValidateArticles validateArticles = new ValidateArticles(getApplicationContext());
        validateArticles.showarticles();

        a = validateArticles.getTypename();
        b = validateArticles.getDesciption();
        c = validateArticles.getImaeurl();
        Articleid = validateArticles.getArticleid();
        listView.addHeaderView(textView);
        ValidationlistAdapter customCountryList = new ValidationlistAdapter(this, a, b, c);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                skeletonScreen.hide();
                listView.setAdapter(customCountryList);
            }
        }, 2000);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ValidateArticlesActivity.this, CheckArticlesActivity.class);
                intent.putExtra("articleid", Articleid.get(position - 1));
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "You Selected article id " + Articleid.get(position - 1), Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public void onBackPressed() {
      Intent intent=new Intent(ValidateArticlesActivity.this,MainActivity.class);
      startActivity(intent);
        super.onBackPressed();
    }
}