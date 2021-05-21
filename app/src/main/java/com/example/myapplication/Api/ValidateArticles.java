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

public class ValidateArticles {

    Context context;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    Boolean success;
    List<String> desciption=new ArrayList<>();
    List<String> typename=new ArrayList<>();
    List<String> imaeurl=new ArrayList<>();
    List<String> articleid=new ArrayList<>();
    SharedPreferences sharedPreferences;
    public ValidateArticles(Context context)
    {
        this.context=context;
    }
    public  List<String> getDesciption() {
        return desciption;
    }

    public void setDesciption( List<String> desciption) {
        this.desciption = desciption;
    }

    public  List<String> getTypename() {
        return typename;
    }

    public void setTypename( List<String> typename) {
        this.typename = typename;
    }

    public List<String> getImaeurl() {
        return imaeurl;
    }

    public void setImaeurl( List<String> imaeurl) {
        this.imaeurl = imaeurl;
    }

    public  List<String> getArticleid() {
        return articleid;
    }

    public void setArticleid( List<String> articleid) {
        this.articleid = articleid;
    }
    public  void  checkedarticles(int id,String status)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, AllApi.validatearticles, response ->
        {
            try {
                JSONObject jsonObject=new JSONObject(response);
                setSuccess(jsonObject.getBoolean("success"));
            //    Toast.makeText(context,"working"+jsonObject.get("success"),Toast.LENGTH_SHORT).show();

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
                map.put("articleid",String.valueOf(id));
                map.put("status",status);
                return  map;

            }
        };
        RequestQueue queue= Volley.newRequestQueue(context);

        queue.add(stringRequest);
    }
public  void showarticles()
{
    sharedPreferences =context.getSharedPreferences("allArticles",0);
    StringRequest stringRequest=new StringRequest(Request.Method.GET, AllApi.showallarticles, response ->
    {
        try {
            JSONObject jsonObject=new JSONObject(response);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("article", jsonObject.toString()).commit();
            JSONArray jsonArray=jsonObject.getJSONArray("Articles");

            int n = jsonArray.length();
            Toast.makeText(context,"working"+jsonObject.get("success"),Toast.LENGTH_SHORT).show();
            for(int i=0;i<n;i++) {
                JSONObject JObject = jsonArray.getJSONObject(i);
                typename.add(JObject.get("type_name").toString());
                desciption.add(JObject.get("desc").toString());
                imaeurl.add(JObject.get("image_url").toString());
                articleid.add(JObject.get("article_id").toString());
            }
            setArticleid(articleid);
            setDesciption(desciption);
            setTypename(typename);
            setImaeurl(imaeurl);
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
            return  map;

        }
    };
    RequestQueue queue= Volley.newRequestQueue(context);

    queue.add(stringRequest);
}


}
