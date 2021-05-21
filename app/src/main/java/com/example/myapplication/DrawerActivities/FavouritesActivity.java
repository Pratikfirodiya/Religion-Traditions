package com.example.myapplication.DrawerActivities;

import android.animation.ArgbEvaluator;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapters.CardAdapter;
import com.example.myapplication.Adapters.FavouritesAdapter;
import com.example.myapplication.Models.FavouritesModel;
import com.example.myapplication.Models.Modelview;
import com.example.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ramotion.foldingcell.FoldingCell;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends  AppCompatActivity {
    ViewPager viewPager;
    FoldingCell fc;
    TextView step1,step2,step3,step4;
    FavouritesAdapter adapter;
    SharedPreferences sharedPreferences;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    List<FavouritesModel> models=new ArrayList<>();
    String desc,imageurl,steps;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        getSupportActionBar().setTitle("Favourites");
        step1=findViewById(R.id.step1_fav);
        step2=findViewById(R.id.step2_fav);
        step3=findViewById(R.id.step3_fav);
        step4=findViewById(R.id.step4_fav);
        viewPager = findViewById(R.id.viewPager_fav);
        sharedPreferences = getApplicationContext().getSharedPreferences("fav", 0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("models", "");
       // Toast.makeText(getApplicationContext(),"Added to favourites"+json,Toast.LENGTH_SHORT).show();
        Type type = new TypeToken<List<FavouritesModel>>(){}.getType();
        models = gson.fromJson(json, type);

      //  models.add(new FavouritesModel("https://images.ctfassets.net/hrltx12pl8hq/3MbF54EhWUhsXunc5Keueb/60774fbbff86e6bf6776f1e17a8016b4/04-nature_721703848.jpg?fit=fill&w=480&h=270", "Sticker", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
     //   models.add(new FavouritesModel("https://image.shutterstock.com/image-photo/mountains-under-mist-morning-amazing-260nw-1725825019.jpg", "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        adapter = new FavouritesAdapter(models, getApplicationContext());
        viewPager.setAdapter(adapter);
        steps=  models.get(0).getSteps();
        String stepss[]=steps.split(";");
        step1.setText(stepss[0]);
        step2.setText(stepss[1]);
        step3.setText(stepss[2]);
        step4.setText(stepss[3]);

        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )

                    );
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(  (Integer) argbEvaluator.evaluate(
                            positionOffset,
                            colors[position],
                            colors[position + 1])));
                    changeStatusBarColor((Integer) argbEvaluator.evaluate(
                            positionOffset,
                            colors[position],
                            colors[position + 1]));
                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {
              steps=  models.get(position).getSteps();
                String stepss[]=steps.split(";");
                step1.setText(stepss[0]);
                step2.setText(stepss[1]);
                step3.setText(stepss[2]);
                step4.setText(stepss[3]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fc = (FoldingCell) findViewById(R.id.folding_cell_fav);
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);

            }
        });

    }

    private void changeStatusBarColor(int color){
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }
}