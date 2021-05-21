package com.example.myapplication.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.myapplication.Festival_item;
import com.example.myapplication.Models.FavouritesModel;
import com.example.myapplication.Models.Modelview;
import com.example.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CardAdapter extends PagerAdapter  {
    private List<Modelview> models;
    public List<FavouritesModel> models1=new ArrayList<>();
    public  List<FavouritesModel> models2=new ArrayList<>();
    private LayoutInflater layoutInflater;
    SharedPreferences sharedPreferences;
    private Context context;


    public CardAdapter(List<Modelview> models, Context context) {
        this.models = models;
        this.context = context;


    }
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);

    }
    @SuppressLint("ClickableViewAccessibility")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);
        ImageView imageView;
        TextView title, desc;
        Button b;
        ToggleButton fav;
        fav=view.findViewById(R.id.button_favorite);
        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);
        desc.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                desc.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        desc.setMovementMethod(new ScrollingMovementMethod());
        fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    sharedPreferences = context.getSharedPreferences("fav", 0);

                   Gson gson1 = new Gson();
                    String json1 = sharedPreferences.getString("models", "");
                    Type type = new TypeToken<List<FavouritesModel>>(){}.getType();
                    models1 = gson1.fromJson(json1, type);
                    models1.add(new FavouritesModel(models.get(position).getImageUrl(),models.get(position).getSteps() ,models.get(position).getDesc()));
                    SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(models1);
                 //   Toast.makeText(context,"Added to favourites"+json,Toast.LENGTH_SHORT).show();
                    prefsEditor.putString("models", json);
                    prefsEditor.apply();

                }else {

                    sharedPreferences = context.getSharedPreferences("fav", 0);
                    Gson gson1 = new Gson();
                    String json1 = sharedPreferences.getString("models", "");
                    Type type = new TypeToken<List<FavouritesModel>>(){}.getType();
                    models1 = gson1.fromJson(json1, type);
                    for (int i=0;i<models1.size();i++)
                    {
                        Toast.makeText(context,models1.get(i).getImageUrl(),Toast.LENGTH_LONG).show();
                        if(models1.get(i).getImageUrl().equals(models.get(position).getImageUrl()))
                        {
                            models1.remove(i);
                          //  Toast.makeText(context,"removed from favourites",Toast.LENGTH_SHORT).show();
                        }
                    }
                    SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(models1);

                    prefsEditor.putString("models", json);
                    prefsEditor.apply();
                }
            }
        });
//         b=view.findViewById(R.id.viewsteps);
//         b.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//
//             }
//         });

        Glide.with(context)
                .load(models.get(position).getImageUrl())
                .into(imageView);
       // imageView.setImageResource(models.get(position).getImage());
     //   title.setText(models.get(position).getTitle());
        desc.setText(models.get(position).getDesc());
       view.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               fav.setChecked(true);
               JSONObject jsonObject=new JSONObject();
               sharedPreferences = context.getSharedPreferences("fav", 0);
               try {
                   jsonObject.put("imageurl",models.get(position).getImageUrl());
                   jsonObject.put("desc",models.get(position).getDesc());
                   jsonObject.put("steps",models.get(position).getSteps());

               } catch (JSONException e) {
                   e.printStackTrace();
               }
               JSONArray jsonArray = new JSONArray();
               jsonArray.put(jsonObject);
               JSONObject studentsObj = new JSONObject();
               try {
                   studentsObj.put("Students", jsonArray);
               } catch (JSONException e) {
                   e.printStackTrace();
               }
               String jsonStr = studentsObj.toString();

          //     System.out.println("jsonString: "+jsonStr);

               return false;
           }
       });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Toast.makeText(context,"sdsdk"+ models.get(position).getImageUrl(),Toast.LENGTH_SHORT).show();
           //     Intent intent = new Intent(context, DetailActivity.class);
             //   intent.putExtra("param", models.get(position).getTitle());
              //  context.startActivity(intent);
                // finish();
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }



}
