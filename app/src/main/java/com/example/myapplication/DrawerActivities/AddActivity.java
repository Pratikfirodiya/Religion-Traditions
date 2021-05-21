package com.example.myapplication.DrawerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Api.Addarticles;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddActivity extends AppCompatActivity implements  View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner Religionspinner,Typespinner;
    Button approvebutton;
    EditText typename,desc,step1,step2,step3,step4,imageurl;
    SharedPreferences sharedPreferences,pref;
    LinearLayout relativeLayout;
    List<String> selectReligion,selectType;
  public   int userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setTitle("Add");
        getDataFromPref();


        Religionspinner=findViewById(R.id.religionspinner);
        Typespinner=findViewById(R.id.typespinner);
        approvebutton=findViewById(R.id.approvebutton);
        relativeLayout=findViewById(R.id.rladdactivity);
        typename=findViewById(R.id.typename_add);
        desc=findViewById(R.id.desc_add);
        step1=findViewById(R.id.steps_add);
//        step2=findViewById(R.id.step2_add);
//        step3=findViewById(R.id.step3_add);
//        step4=findViewById(R.id.step4_add);
        imageurl=findViewById(R.id.imageurl_add);
        Religionspinner.setOnItemSelectedListener(this);
        Typespinner.setOnItemSelectedListener(this);
        approvebutton.setOnClickListener(this);

        selectType = new ArrayList<String>();
          selectType.add("Festival");
          selectType.add("Occasion");
        ArrayAdapter<String> RelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, selectReligion);
        RelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Religionspinner.setAdapter(RelAdapter);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, selectType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Typespinner.setAdapter(typeAdapter);

    }

    public void getDataFromPref() {
        selectReligion= new ArrayList<String>();
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
                selectReligion.add(JObject.get("religion_name").toString());
            }


        }  catch (JSONException ignored)
        {
            //   Toast.makeText(context,"hello1"+ignored,Toast.LENGTH_LONG).show();
        }
        pref=getApplicationContext().getSharedPreferences("credentials", 0);
       userid=pref.getInt("id",0);
    }


    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        int i=1;

        ProgressDialog diag = new ProgressDialog(this);
        diag.setIndeterminate(true);
        diag.setTitle("hello");
        diag.setMessage("adding data");
        diag.setCancelable(false);
        //here is the trick:
       // diag.setIndeterminateDrawable(getResources().getDrawable(R.drawable.animfordialog, null));
        diag.show();
        String step11=       step1.getText().toString()+";";
        String step12=       step2.getText().toString()+";";
        String step13=     step3.getText().toString()+";";
        String step14 =    step4.getText().toString()+";";
//        step11.concat(";");
//        step12.concat(";");
//        step13.concat(";");
//        step14.concat(";");
        String steps=step11.concat(step12.concat(step13.concat(step14)));
        Addarticles articles=new Addarticles(getApplicationContext());
        articles.addArticle(Religionspinner.getSelectedItem().toString(),userid,Typespinner.getSelectedItem().toString(),typename.getText().toString(),desc.getText().toString(),steps,imageurl.getText().toString());
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(articles.getSuccess()!=null) {
                    diag.hide();
                    Snackbar snackbar = Snackbar
                            .make(relativeLayout, "Data will be Validated Soon", Snackbar.LENGTH_LONG)
                            .setAction("Check Status", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(AddActivity.this, MycontributionActivity.class);
                                    startActivity(intent);
                                }
                            });

                    snackbar.show();
                }
            }
        }, 3000 );

    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}