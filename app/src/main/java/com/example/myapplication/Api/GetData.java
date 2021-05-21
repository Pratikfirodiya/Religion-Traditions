package com.example.myapplication.Api;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GetData extends AsyncTask<Void,Void,Void> {
    Context context;
    ProgressDialog p;
    List<String> arr2 = new ArrayList<String>();
    String arrr[]=new String[4];
   // SharedPreferences pref;
    String festivals[]=new String[10];
    Set<String> storeoccasion =new HashSet<String>();
            Set<String> storefestivals= new HashSet<String>();
    String occasion[]=new String[10];
    SharedPreferences sharedPreferences,festival,occasions,all;

    public GetData(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("localpref", 0);
        festival=context.getSharedPreferences("festivals",0);
        occasions=context.getSharedPreferences("ocaasions",0);
        all=context.getSharedPreferences("all",0);
    }

public void getDataFromPref() {
      //  SharedPreferences sharedPreferences = getSharedPreferences("localpref", MODE_PRIVATE);
        String value = sharedPreferences.getString("pref_data", "");
        try {
            JSONObject jsonObject = new JSONObject(value);
            JSONArray  jsonArray  = jsonObject.getJSONArray("data");
            int n = jsonArray.length();
            for(int i=0;i<n;i++)
            {
                JSONObject JObject = jsonArray.getJSONObject(i);
                arr2.add(JObject.get("religion_name").toString());
              //  Toast.makeText(context,"hello1"+JObject.get("religion_name").toString(),Toast.LENGTH_LONG).show();
                //   storereligions.add(arr2.get(i));
             //   arrr[i]=arr2.get(i);


            }
            //    barHandler.hide();


        }  catch (JSONException ignored)
        {
            Toast.makeText(context,"hello1"+ignored,Toast.LENGTH_LONG).show();
        }

    }
public void storeDataToPref(JSONObject jsonObject) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("pref_data", jsonObject.toString()).commit();
}
    public void getReliion()
    {
         //  ProgressBarHandler barHandler=new ProgressBarHandler(context);
       //    barHandler.show();

    }
    public void getstoredfestivals()
    {

    }
    public String[] arr()
    {
        return  arrr;
    }
public String[] festivalss()
{
    return  festivals;
}
public  String[] occasions()
{
    return  occasion;
}


    public void executeApi(String rname, Context context)
    {

     //   JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,AllApi.fetchreligion,)
        StringRequest stringRequest=new StringRequest(Request.Method.POST, AllApi.fetchreligion, response ->
        {

            try {
                JSONObject object = new JSONObject(response);
                SharedPreferences.Editor editor = all.edit();
                editor.putString("all", object.toString()).commit();
                JSONArray  jsonArray  = object.getJSONArray("festivaldata");
                JSONArray jsonArray1=object.getJSONArray("occasiondata");

                int n = jsonArray.length();
                int n1=jsonArray1.length();
                Toast.makeText(context,"api working",Toast.LENGTH_SHORT).show();
                for(int i=0;i<n;i++)
                {
                    JSONObject JObject = jsonArray.getJSONObject(i);
               //     festivals[i]=   JObject.get("festival_name").toString();

                    storefestivals.add( JObject.get("festival_name").toString());

                }
                setStorefestivals(storefestivals);

                for(int i=0;i<n1;i++)
                {
                    JSONObject object1=jsonArray1.getJSONObject(i);
                  //  occasion[i]=object1.get("occasion_name").toString();
                    storeoccasion.add(object1.get("occasion_name").toString());
                }
                setStoreoccasion(storeoccasion);

            }  catch (JSONException ignored)
            {
                Toast.makeText(context,"hello1"+ignored,Toast.LENGTH_LONG).show();
            }

        },error -> {

        })  {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("rname",rname);
                return  map;

            }
        };
        RequestQueue queue= Volley.newRequestQueue(context);

        queue.add(stringRequest);
    }




    @Override
    protected Void doInBackground(Void... voids) {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, AllApi.Showreligions, response ->
        {

            try {
                JSONObject object = new JSONObject(response);
                storeDataToPref(object);
                JSONArray  jsonArray  = object.getJSONArray("data");
                int n = jsonArray.length();
                for(int i=0;i<n;i++)
                {
                    JSONObject JObject = jsonArray.getJSONObject(i);
                    arr2.add(JObject.get("religion_name").toString());
                  //  Toast.makeText(context,"hello1",Toast.LENGTH_LONG).show();
                 //   storereligions.add(arr2.get(i));
                    arrr[i]=arr2.get(i);


                }
                //    barHandler.hide();


            }  catch (JSONException ignored)
            {
                Toast.makeText(context,"hello1"+ignored,Toast.LENGTH_LONG).show();
            }

        },error -> {

        })  {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                return  map;

            }
        };
        RequestQueue queue= Volley.newRequestQueue(context);

        queue.add(stringRequest);


        return null;
    }
    public void setStorefestivals(Set<String> a)
    {
        SharedPreferences.Editor editor=festival.edit();
        editor.putStringSet("festivals",a).commit();
    }
    public  void setStoreoccasion(Set<String> b)
    {
        SharedPreferences.Editor editor=occasions.edit();
        editor.putStringSet("occasions",b).commit();
    }
}
