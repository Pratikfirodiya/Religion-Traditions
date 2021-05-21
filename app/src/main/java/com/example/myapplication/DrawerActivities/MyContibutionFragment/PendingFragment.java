package com.example.myapplication.DrawerActivities.MyContibutionFragment;

import android.annotation.SuppressLint;
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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Api.Addarticles;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class PendingFragment extends Fragment  implements AdapterView.OnItemClickListener {

   ListView listView;
   SharedPreferences pref;
   TextView t;
    ProgressBar progressBar;
    List<String> pending=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_pending, container, false);

    }

    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=view.findViewById(R.id.lvpending);
        t=view.findViewById(R.id.fetchpen);
        progressBar=view.findViewById(R.id.proresspendin);
        progressBar.setProgress(2000,true);
        Addarticles addarticles=new Addarticles(getContext());
        pref=getContext().getSharedPreferences("credentials", 0);
        addarticles.showarticlesbyid(pref.getInt("id",0));


       pending= addarticles.getPending();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                if(pending.isEmpty())
                {
                    t.setText("Nothing To show");
                    t.setVisibility(View.VISIBLE);
                }


                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,pending);
                listView.setAdapter(arrayAdapter);
            }
        }, 2000 );

       //

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(),""+listView.getItemAtPosition(position),Toast.LENGTH_LONG).show();
    }
}