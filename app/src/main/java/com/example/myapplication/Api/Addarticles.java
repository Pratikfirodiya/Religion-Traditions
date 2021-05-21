package com.example.myapplication.Api;

import android.content.Context;
import android.content.SharedPreferences;
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
import java.util.List;
import java.util.Map;

public class Addarticles {
    Context context;
    SharedPreferences articles;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    Boolean success=false;
    List<String> pending=new ArrayList<>();
    List<String>approve=new ArrayList<>();
    List<String> reject=new ArrayList<>();
    public Addarticles(Context context)
    {
        this.context=context;

    }

    public void setPending(List<String> pending) {
        this.pending = pending;
    }

    public List<String> getPending() {
        return pending;
    }
    public void setApprove(List<String> approve) {
        this.approve = approve;
    }

    public List<String> getApprove() {
        return approve;
    }
    public void setReject(List<String> reject) {
        this.reject = reject;
    }

    public List<String> getReject() {
        return reject;
    }

    public void showarticlesbyid(int id)
    {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, AllApi.showarticlesbyid, response ->
        {
            try {
                JSONObject jsonObject=new JSONObject(response);
                JSONArray jsonArray=jsonObject.getJSONArray("Articles");
                int n = jsonArray.length();
                for(int i=0;i<n;i++) {
                    JSONObject JObject = jsonArray.getJSONObject(i);
                     if(JObject.get("status").toString().equalsIgnoreCase("Pending"))
                     {
                           pending.add(JObject.get("type_name").toString());
                     }
                    if(JObject.get("status").toString().equalsIgnoreCase("Approved"))
                    {
                        approve.add(JObject.get("type_name").toString());
                    }
                    if(JObject.get("status").toString().equalsIgnoreCase("Rejected"))
                    {
                        reject.add(JObject.get("type_name").toString());
                    }
                }
                setPending(pending);
                setApprove(approve);
                setReject(reject);
            }  catch (JSONException ignored)
            {
                Toast.makeText(context,ignored.toString(),Toast.LENGTH_SHORT).show();
            }

        },error -> {

        })  {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();


                map.put("id",String.valueOf(id));


                //map.put("number",)


                return  map;

            }
        };
        RequestQueue queue= Volley.newRequestQueue(context);

        queue.add(stringRequest);
    }

    public void addArticle(String rname,int id, String type, String typename,String desc,String steps,String imageurl )
    {
        articles=context.getSharedPreferences("userarticles",0);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, AllApi.addarticles, response ->
        {
            try {
                JSONObject jsonObject=new JSONObject(response);
                if(jsonObject.getBoolean("success"))
                {
                    setSuccess(true);
                }
                JSONObject jsonObject1=jsonObject.getJSONObject("article");
                Toast.makeText(context,"added",Toast.LENGTH_SHORT).show();

            }  catch (JSONException ignored)
            {
                Toast.makeText(context,ignored.toString(),Toast.LENGTH_SHORT).show();
            }

        },error -> {

        })  {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();

                map.put("rname",rname);
                map.put("id",String.valueOf(id));
                map.put("type",type);
                map.put("typename",typename);
                map.put("desc",desc);
                map.put("steps",steps);
                map.put("imageurl",imageurl);

                //map.put("number",)


                return  map;

            }
        };
        RequestQueue queue= Volley.newRequestQueue(context);

        queue.add(stringRequest);
    }
}

