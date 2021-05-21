package com.example.myapplication;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.example.myapplication.Adapters.GridAdapter;
import com.example.myapplication.Api.GetData;
import com.example.myapplication.Navigationdrawer.DrawerActivity;
import com.example.myapplication.ReligionActivity.HinduActivity;
import com.example.myapplication.ReligionActivity.JainActivity;
import com.example.myapplication.ReligionActivity.MuslimActivity;
import com.example.myapplication.ReligionActivity.SikhimActivity;
import com.example.myapplication.fragment.Festivalfrag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends DrawerActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    SharedPreferences pref1;
    GridView grid;
    Button button;
    GridAdapter obj;
     String array[];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        View v=inflater.inflate(R.layout.activity_main,null,false);
          drawer.addView(v,0);
//        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Home");
        grid=(GridView)findViewById(R.id.datagrid);
        pref1 = getApplicationContext().getSharedPreferences("rname", 0);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Religionset", 0); //
        Set<String> fetch =   pref.getStringSet("Religion",null);
        List<String> list = new ArrayList<String>(fetch);
         array = new String[list.size()];
        for(int j =0;j<list.size();j++){
            array[j] = list.get(j);
        }
         obj=new GridAdapter(this,array);
        grid.setAdapter(obj);

        grid.setOnItemClickListener(this);
//        for(int i = 0 ; i < list.size() ; i++){
////            Toast.makeText( MainActivity.this,list.get(i),Toast.LENGTH_LONG).show();
//            addbutton(list.get(i));
//        }

//        SharedPreferences.Editor editor = pref.edit();
//         editor.clear();
//        editor.commit();



    }



    private void addbutton(String s) {

//        GridLayout gridLayout=findViewById(R.id.gl);
//        button = new Button(this);
//        button.setText(s);
//        button.setTag(s);
//        button.setBackground(this.getResources().getDrawable(R.drawable.roundbutton));
//
//        gridLayout.addView(button);
//        button.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        {
//          String tag= (String) v.getTag();
//            switch(tag) {
//                case "Hinduism":
//                 Intent intent=new Intent(MainActivity.this, HinduActivity.class);
//                 intent.putExtra("tag",tag);
//                 startActivity(intent);
//                    break;
//
//                case "Muslim":
//                    Intent intent1=new Intent(MainActivity.this, MuslimActivity.class);
//                    intent1.putExtra("tag",tag);
//                    startActivity(intent1);
//                    break;
//
//                case "Sikhim":
//                    Intent intent2=new Intent(MainActivity.this, SikhimActivity.class);
//                    intent2.putExtra("tag",tag);
//                    startActivity(intent2);
//                    break;
//
//                case "Jainism":
//                    Intent intent3=new Intent(MainActivity.this, JainActivity.class);
//                    intent3.putExtra("tag",tag);
//                    startActivity(intent3);
//                    break;
//
//                default: break;
            }
        }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       String selectedItem=array[position];
    //   Toast.makeText(getApplicationContext(),selectedItem,Toast.LENGTH_LONG).show();
     //   GetData obj=new GetData(getApplicationContext());
       // obj.executeApi(selectedItem,getApplicationContext());
        GetData getData=new GetData(getApplicationContext());
        getData.executeApi(selectedItem,getApplicationContext());
        SharedPreferences.Editor editor = pref1.edit();
        editor.putString("Rname",selectedItem);
        editor.commit();
         Intent intent=new Intent(MainActivity.this,HinduActivity.class);
         intent.putExtra("tag",selectedItem);
           startActivity(intent);

    }
}
