package com.example.myapplication.Models;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Api.AllApi;
import com.example.myapplication.AuthenticationActivities.LoginActivity;
import com.example.myapplication.utils.ProgressBarHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserModel {

      String firstName;
      String lastName;
     String email;
     int id;
     String password;
   public   Boolean status;

public void setid(int id){ this.id=id;}
public int getid(){return id;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {

    this.status = status;
    }










    public void register(String firstName1, String lastName1, String email1, String password1,Context context)
    {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, AllApi.Register, response ->
        {
            try {
                JSONObject jsonObject=new JSONObject(response);
                if(jsonObject.getBoolean("success"))
                {
                    setStatus(jsonObject.getBoolean("success"));
                }
                else
                {
                   setStatus(jsonObject.getBoolean("success"));
                }

            }  catch (JSONException ignored)
            {

            }

        },error -> {

        })  {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("firstname",firstName1);
                map.put("lastname",lastName1);
                //map.put("number",)
                map.put("email",email1);
                map.put("password",password1);
                return  map;

            }
        };
        RequestQueue queue= Volley.newRequestQueue(context);

        queue.add(stringRequest);
    }
    public void login( String email1, String password1,Context context)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, AllApi.LOGIN, response ->
        {
            try {
                JSONObject jsonObject=new JSONObject(response);
               JSONObject jsonObject1= jsonObject.getJSONObject("user");
                if(jsonObject.getBoolean("success"))
                {

                    setStatus(jsonObject.getBoolean("success"));
                    setid(jsonObject1.getInt("id"));

                }
                else
                {

                    setStatus(jsonObject.getBoolean("success"));

                }

            }  catch (JSONException ignored)
            {
                Toast.makeText(context,"heee"+ignored,Toast.LENGTH_LONG).show();
            }

        },error -> {

        })  {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("email",email1);
                map.put("password",password1);
                return  map;

            }
        };
        RequestQueue queue= Volley.newRequestQueue(context);

        queue.add(stringRequest);
    }
}
