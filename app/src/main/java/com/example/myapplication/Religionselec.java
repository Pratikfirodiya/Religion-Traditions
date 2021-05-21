package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Api.GetData;
import com.example.myapplication.utils.ProgressBarHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Religionselec extends AppCompatActivity implements View.OnClickListener {

      ListView lv;
      ArrayAdapter<String> arrayAdapter;
    List<String> arr2 = new ArrayList<String>();
      Button button;
      SharedPreferences pref,sharedPreferences;
    String[] rel2=new String[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religionselec);
        lv = findViewById(R.id.listview);
        getSupportActionBar().setTitle("Religion Selection");
     //   GetData getData=new GetData(getApplicationContext());
      //  getData.execute();
       // getData.storeDataToPref();
      // rel2=getData.arr();
        getDataFromPref();
        pref = getApplicationContext().getSharedPreferences("Religionset", 0); //

//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                callAdapter();
//
//            }
//        }, 2000 );
callAdapter();


        button = findViewById(R.id.btn_show_me);
        button.setOnClickListener(this);



    }

    private void callAdapter()
    {
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, rel2);
            lv.setAdapter(arrayAdapter);
    }
    public void getDataFromPref() {
        //  SharedPreferences sharedPreferences = getSharedPreferences("localpref", MODE_PRIVATE);
        sharedPreferences = getApplicationContext().getSharedPreferences("localpref", 0);
        String value = sharedPreferences.getString("pref_data", "");
        try {
            JSONObject jsonObject = new JSONObject(value);
            JSONArray jsonArray  = jsonObject.getJSONArray("data");
            int n = jsonArray.length();
            for(int i=0;i<n;i++)
            {
                JSONObject JObject = jsonArray.getJSONObject(i);
                arr2.add(JObject.get("religion_name").toString());
              //  Toast.makeText(,"hello1"+JObject.get("religion_name").toString(),Toast.LENGTH_LONG).show();
                //   storereligions.add(arr2.get(i));
                   rel2[i]=arr2.get(i);


            }
            //    barHandler.hide();


        }  catch (JSONException ignored)
        {
         //   Toast.makeText(context,"hello1"+ignored,Toast.LENGTH_LONG).show();
        }

    }
    public void onClick(View v) {

        SharedPreferences.Editor editor = pref.edit();
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < lv.getCount(); i++) {
            if (lv.isItemChecked(i)) {
                set.add( ""+ lv.getItemAtPosition(i));
            }
            editor.putStringSet("Religion",set);
            editor.commit();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}