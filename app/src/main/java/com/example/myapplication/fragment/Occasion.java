package com.example.myapplication.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.example.myapplication.Api.GetData;
import com.example.myapplication.Festival_item;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Occasion extends Fragment implements AdapterView.OnItemClickListener {
    String rname;
    SharedPreferences pref2,occasions;
    String occ[];
    ListView listView;
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
        return inflater.inflate(R.layout.fragment_occasion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      //  String[] city={"MahaShivratri","RakshaBandhan"};
        frameLayout=view.findViewById(R.id.occasionframelayout);
        skeletonScreen = Skeleton.bind(frameLayout)
                .load(R.layout.layout_placeholder)
                .show();
         listView=view.findViewById(R.id.lv2);
        pref2 = getContext().getSharedPreferences("name", 0);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                getoccasions();
                callAdapter();
                skeletonScreen.hide();
            }
        }, 500 );
        listView.setOnItemClickListener(this);
    }
    public void callAdapter()
    {
        for (int i=0;i<occ.length;i++)
        {           if(occ[i]==null)
        {
            occ[i]="";
        }
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,occ);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent =new Intent(getActivity(), Festival_item.class);
        intent.putExtra("festival",""+listView.getItemAtPosition(position));
        SharedPreferences.Editor editor=pref2.edit();
        editor.putString("value",listView.getItemAtPosition(position).toString()).commit();
        intent.putExtra("festival",""+listView.getItemAtPosition(position));
        startActivity(intent);
    }
    public  void getoccasions()
    {
        occasions=getContext().getSharedPreferences("ocaasions",0);
        Set<String> all=occasions.getStringSet("occasions",null);
        List<String> list = new ArrayList<String>(all);
        occ=new String[list.size()];
        for(int i=0;i<list.size();i++)
        {
            occ[i]=list.get(i);
        }
    }
}