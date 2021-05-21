package com.example.myapplication.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.example.myapplication.Api.GetData;
import com.example.myapplication.Festival_item;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Festivalfrag extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    String festival[];
    String city[];
    String rname;
   SharedPreferences pref2,festivals;
   SkeletonScreen skeletonScreen;
   FrameLayout frameLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      //  rname=getArguments().getString("rname");
        return inflater.inflate(R.layout.fragment_festivalfrag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       pref2 = getContext().getSharedPreferences("name", 0);
//        rname=pref1.getString("Rname","");
//        festival=getData.festivalss();
        frameLayout=view.findViewById(R.id.festivalframe);
        skeletonScreen = Skeleton.bind(frameLayout)
                .load(R.layout.layout_placeholder)
                .show();
         city= new String[]{"Diwali", "Holi", "GudiPadwa", "Dussehra", "Diwali", "Holi", "GudiPadwa", "Dussehra"};
         listView=view.findViewById(R.id.lv1);
        getfestivals();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              skeletonScreen.hide();
                callAdapter();
            }
        }, 500 );


        listView.setOnItemClickListener(this);

    }

    public void callAdapter() {
        for (int i=0;i<festival.length;i++)
        {           if(festival[i]==null)
        {
            festival[i]="";
        }
        }

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,festival);
        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent =new Intent(getActivity(), Festival_item.class);
        SharedPreferences.Editor editor=pref2.edit();
        editor.putString("value",listView.getItemAtPosition(position).toString()).commit();
        intent.putExtra("festival",""+listView.getItemAtPosition(position));
        startActivity(intent);

    }
    public  void getfestivals()
    {
        festivals=getContext().getSharedPreferences("festivals",0);
        Set<String> all=festivals.getStringSet("festivals",null);
        List<String> list = new ArrayList<String>(all);
        festival=new String[list.size()];
        for(int i=0;i<list.size();i++)
        {
            festival[i]=list.get(i);
        }
    }
}