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


public class RejectedFragment extends Fragment implements AdapterView.OnItemClickListener {

    TextView t;
    ListView listView;
    SharedPreferences pref;
    ProgressBar progressBar;
    List<String> reject=new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rejected, container, false);
    }
    @SuppressLint("NewApi")
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] city={"Rejected","Rejected","Rejected","Rejected"};
        listView=view.findViewById(R.id.lvreject);
        t=view.findViewById(R.id.fetchrej);
        progressBar=view.findViewById(R.id.proressreject);
        progressBar.setProgress(2000,true);
        Addarticles addarticles=new Addarticles(getContext());
        pref=getContext().getSharedPreferences("credentials", 0);
        addarticles.showarticlesbyid(pref.getInt("id",0));
       reject= addarticles.getReject();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                if(reject.isEmpty())
                {
                    t.setText("Nothing To show");
                    t.setVisibility(View.VISIBLE);
                }
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,reject);

                listView.setAdapter(arrayAdapter);
            }
        }, 1000 );

        listView.setOnItemClickListener(this);
    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(),""+listView.getItemAtPosition(position),Toast.LENGTH_LONG).show();
    }
}