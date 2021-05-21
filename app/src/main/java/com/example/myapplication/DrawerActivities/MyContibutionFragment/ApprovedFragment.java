package com.example.myapplication.DrawerActivities.MyContibutionFragment;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.Api.Addarticles;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ApprovedFragment extends Fragment implements AdapterView.OnItemClickListener {

  ListView listView;
  SharedPreferences pref;

  TextView t;
  ProgressBar progressBar;
  FrameLayout frameLayout;
    List<String> approve=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_approved, container, false);
    }

    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] city={"Approved","Approved","Approved","Approved"};
        listView=view.findViewById(R.id.lvapprove);
        frameLayout=view.findViewById(R.id.aprrveframelayout);
         t=view.findViewById(R.id.fetch);
         progressBar=view.findViewById(R.id.proressapprove);
         progressBar.setProgress(2000,true);
        Addarticles addarticles=new Addarticles(getContext());
        pref=getContext().getSharedPreferences("credentials", 0);
        addarticles.showarticlesbyid(pref.getInt("id",0));
        approve= addarticles.getApprove();
        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipe_layout);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(approve.isEmpty())
                {

                    t.setText("Nothing To show");
                    t.setVisibility(View.VISIBLE);
                }
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,approve);
                listView.setAdapter(arrayAdapter);
                pullToRefresh.setRefreshing(false);
            }
        });
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
           setaadapter();
            }
        }, 1000 );

        listView.setOnItemClickListener(this);
    }
public  void  setaadapter()
{
    progressBar.setVisibility(View.INVISIBLE);
    if(approve.isEmpty())
    {

        t.setText("Nothing To show");
        t.setVisibility(View.VISIBLE);
    }
    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,approve);

    listView.setAdapter(arrayAdapter);
}
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        LayoutInflater inflater = getLayoutInflater();
//        View layouttoast = inflater.inflate(R.layout.row,null);
//        Toast mytoast = new Toast(getContext());
//        mytoast.setView(layouttoast);
//        mytoast.setDuration(Toast.LENGTH_LONG);
//        mytoast.show();
        Toast.makeText(getActivity(),""+listView.getItemAtPosition(position),Toast.LENGTH_LONG).show();
    }
}