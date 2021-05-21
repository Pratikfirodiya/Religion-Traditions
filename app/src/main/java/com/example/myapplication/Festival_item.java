package com.example.myapplication;

import android.animation.ArgbEvaluator;
import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.Adapters.CardAdapter;
import com.example.myapplication.Api.Config;
import com.example.myapplication.Models.Modelview;
import com.example.myapplication.fragment.Festivalfrag;
import com.example.myapplication.utils.UserSteps;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.ramotion.foldingcell.FoldingCell;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Rational;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormView;
import ernestoyaquello.com.verticalstepperform.listener.StepperFormListener;

public class Festival_item extends AppCompatActivity implements View.OnClickListener {


     FoldingCell fc;
     TextView step1,step2,step3,step4;
    ViewPager viewPager;
    SharedPreferences sharedPreferences,pref;
    CardAdapter adapter;
    List<Modelview> models=new ArrayList<>();
    Integer[] colors = null;
    int id;
    RelativeLayout  relativeLayout;
    String desc,imageurl,steps;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24  );
        TextView mTitle = findViewById(R.id.toolbar_title);
        relativeLayout=findViewById(R.id.rlbg);
        step1=findViewById(R.id.step1);
        step2=findViewById(R.id.step2);
        step3=findViewById(R.id.step3);
        step4=findViewById(R.id.step4);

        mTitle.setText(getIntent().getStringExtra("festival"));



     //   models.add(new Modelview("https://image.shutterstock.com/image-photo/mountains-under-mist-morning-amazing-260nw-1725825019.jpg", "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
       // models.add(new Modelview("https://images.ctfassets.net/hrltx12pl8hq/3MbF54EhWUhsXunc5Keueb/60774fbbff86e6bf6776f1e17a8016b4/04-nature_721703848.jpg?fit=fill&w=480&h=270", "Sticker", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
        //models.add(new Modelview(R.drawable.hinduism, "Poster", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."));
        //models.add(new Modelview(R.drawable.hinduism, "Namecard", "Business cards are cards bearing business information about a company or individual."));



                viewPager = findViewById(R.id.viewPager);
                loaddata();
                addmodels();
                adapter = new CardAdapter(models, getApplicationContext());
                viewPager.setAdapter(adapter);


        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
             //   getResources().getColor(R.color.color4)
        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
                    relativeLayout.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]));
                    toolbar.setBackgroundColor( (Integer) argbEvaluator.evaluate(
                            positionOffset,
                            colors[position],
                            colors[position + 1]));
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

                Toast.makeText(getApplicationContext(),  models.get(position).getImageUrl(),Toast.LENGTH_SHORT).show();
               // fc.toggle(false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
      //  toolbar.setTitle(getIntent().getStringExtra("festival"));
//        viewsteps=findViewById(R.id.viewsteps);
//        viewsteps.setOnClickListener(this);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//          floatingActionButton=findViewById(R.id.fab2);
//          floatingActionButton.setOnClickListener(this);
       fc = (FoldingCell) findViewById(R.id.folding_cell);
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

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.favourites, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.addtofav:

                Toast
                        .makeText(
                                getApplicationContext(),
                                "Shows share icon",
                                Toast.LENGTH_SHORT)
                        .show();
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }

    public  void togle()
{


//
//            if (fc.isUnfolded()) {
//                viewsteps.setText("Show Steps");
//
//            } else {
//                viewsteps.setText("Hide Steps");
//
//            }
}
    private void addmodels() {
        models.add(new Modelview(imageurl,steps,desc));
        models.add(new Modelview("https://images.ctfassets.net/hrltx12pl8hq/3MbF54EhWUhsXunc5Keueb/60774fbbff86e6bf6776f1e17a8016b4/04-nature_721703848.jpg?fit=fill&w=480&h=270", "Sticker", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
        String stepss[]=steps.split(";");
        step1.setText(stepss[0]);
        step2.setText(stepss[1]);
        step3.setText(stepss[2]);
        step4.setText(stepss[3]);
     //   models.add(new Modelview("https://image.shutterstock.com/image-photo/mountains-under-mist-morning-amazing-260nw-1725825019.jpg", "Namecard", "Business cards are cards bearing business information about a company or individual."));

    }

    public void loaddata() {
        sharedPreferences = getApplicationContext().getSharedPreferences("all", 0);
        pref=getApplicationContext().getSharedPreferences("name",0);
        String item=pref.getString("value","");
        String value = sharedPreferences.getString("all", "");

        try {
            JSONObject jsonObject = new JSONObject(value);
            JSONArray jsonArray  = jsonObject.getJSONArray("festivaldata");
            JSONArray jsonArray1=jsonObject.getJSONArray("occasiondata");
            JSONArray jsonArray2=jsonObject.getJSONArray("Festivalimages");
            JSONArray jsonArray3=jsonObject.getJSONArray("Occasionimages");

            int n = jsonArray.length();
            for(int i=0;i<n;i++)
            {
                JSONObject JObject = jsonArray.getJSONObject(i);
           if(JObject.get("festival_name").equals(item)) {
               id = JObject.getInt("festival_id");
               desc = JObject.get("festival_desc").toString();
               steps = JObject.get("festival_procedure").toString();
               int n1=jsonArray2.length();
               for (int j=0;j<n1;j++)
               {
                   JSONObject JObject1 = jsonArray2.getJSONObject(j);
                   if(JObject1.getInt("festival_id")==id)
                   {
                       imageurl=JObject1.get("image_url").toString();

                   }
               }
           }
            }
            int n2 = jsonArray1.length();
            for(int i=0;i<n2;i++)
            {
                JSONObject JObject = jsonArray1.getJSONObject(i);
                if(JObject.get("occasion_name").equals(item)) {
                    id = JObject.getInt("occasion_id");
                    desc = JObject.get("occasion_desc").toString();
                    steps = JObject.get("occasion_procedure").toString();
                    int n1=jsonArray3.length();
                    for (int j=0;j<n1;j++)
                    {
                        JSONObject JObject1 = jsonArray3.getJSONObject(j);
                        if(JObject1.getInt("occasion_id")==id)
                        {
                            imageurl=JObject1.get("image_url").toString();
                         //   Toast.makeText(getApplicationContext(),"hue"+imageurl,Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }



            //    barHandler.hide();


        }  catch (JSONException ignored)
        {
            //   Toast.makeText(context,"hello1"+ignored,Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View view) {
//        if(view.getId()==R.id.viewsteps) {
//            fc.toggle(false);
//
//            if (fc.isUnfolded()) {
//                viewsteps.setText("Show Steps");
//
//            } else {
//                viewsteps.setText("Hide Steps");
//
//            }
//        }

      //  fc.toggle(false);

//        if (view.getId() == R.id.fab2) {
//            Intent intent=new Intent(Festival_item.this,YoutubeActivity.class);
//            startActivity(intent);
//            if (android.os.Build.VERSION.SDK_INT >= 26) {
//                //Trigger PiP mode
//                try {
//                    Rational rational = new Rational(youTubeView.getWidth(), youTubeView.getHeight());
//
//                    PictureInPictureParams mParams =
//                            new PictureInPictureParams.Builder()
//                                    .setAspectRatio(rational)
//                                    .build();
//
//                    enterPictureInPictureMode(mParams);
//                } catch (IllegalStateException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                Toast.makeText(Festival_item.this, "API 26 needed to perform PiP", Toast.LENGTH_SHORT).show();
//            }
        }

//        if (view.getId() == R.id.fab1) {
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse("https://open.spotify.com/album/3T4tUhGYeRNVUGevb0wThu?highlight=spotify:track:0tgVpDi06FyKpA1z0VMD4v"));
//            intent.setPackage("com.spotify.music");
//            startActivity(intent);
//
//        }


    }



