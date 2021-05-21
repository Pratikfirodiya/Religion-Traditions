package com.example.myapplication.ReligionActivity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.fragment.Festivalfrag;
import com.example.myapplication.fragment.Occasion;
import com.google.android.material.button.MaterialButtonToggleGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HinduActivity extends AppCompatActivity implements View.OnTouchListener {

    final static float STEP = 200;
    TextView mytv;
    float mRatio = 1.0f;
    int mBaseDist;
    float mBaseRatio;
    float fontsize = 13;
    ImageView imageView;
    SharedPreferences sharedPreferences,pref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hindu);
        imageView=findViewById(R.id.relimg);
        mytv = (TextView) findViewById(R.id.intro);

        getSupportActionBar().setTitle(getIntent().getStringExtra("tag"));
        LoadUi();
       // getSupportActionBar().hide();

        MaterialButtonToggleGroup materialButtonToggleGroup = findViewById(R.id.HindutoggleGroup);
       mytv.setMovementMethod(new ScrollingMovementMethod());

        mytv.setTextSize(mRatio + 17);
        if ( materialButtonToggleGroup.getCheckedButtonId()== R.id.Hindubtnfestival) {

            getSupportFragmentManager().beginTransaction().replace(R.id.Hinduframe,new Festivalfrag()).commit();
        }
        materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    if (checkedId == R.id.Hindubtnfestival) {

                        getSupportFragmentManager().beginTransaction().replace(R.id.Hinduframe,new Festivalfrag()).commit();
                    }
                    if (checkedId == R.id.HindubtnOccasion) {

                        getSupportFragmentManager().beginTransaction().replace(R.id.Hinduframe,new Occasion()).commit();
                    }
                }
            }
        });
    }

    public void LoadUi() {
        pref1 = getApplicationContext().getSharedPreferences("rname", 0);
        sharedPreferences = getApplicationContext().getSharedPreferences("localpref", 0);
        String value = sharedPreferences.getString("pref_data", "");
        String Religion=pref1.getString("Rname","");
     //   Toast.makeText(getApplicationContext(),""+Religion,Toast.LENGTH_SHORT).show();
        try {
            JSONObject jsonObject = new JSONObject(value);
            JSONArray jsonArray  = jsonObject.getJSONArray("data");
            int n = jsonArray.length();
            for(int i=0;i<n;i++)
            {
                JSONObject JObject = jsonArray.getJSONObject(i);
               if(JObject.get("religion_name").toString().equals(Religion))
               {
                   Glide.with(getApplicationContext())
                           .load(JObject.get("imageurl"))
                           .into(imageView);
                   mytv.setText(JObject.get("religion_desc").toString());
               }
            }
        }  catch (JSONException ignored)
        {
            //   Toast.makeText(context,"hello1"+ignored,Toast.LENGTH_LONG).show();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getPointerCount() == 2) {
            int action = event.getAction();
            int pureaction = action & MotionEvent.ACTION_MASK;
            if (pureaction == MotionEvent.ACTION_POINTER_DOWN) {
                mBaseDist = getDistance(event);
                mBaseRatio = mRatio;
            } else {
                float delta = (getDistance(event) - mBaseDist) / STEP;
                float multi = (float) Math.pow(2, delta);
                mRatio = Math.min(1024.0f, Math.max(0.1f, mBaseRatio * multi));
                mytv.setTextSize(mRatio + 13);
            }
        }
        return true;
    }

    int getDistance(MotionEvent event) {
        int dx = (int) (event.getX(0) - event.getX(1));
        int dy = (int) (event.getY(0) - event.getY(1));
        return (int) (Math.sqrt(dx * dx + dy * dy));
    }
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

}