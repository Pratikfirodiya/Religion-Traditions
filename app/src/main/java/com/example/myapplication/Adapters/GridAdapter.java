package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class GridAdapter extends BaseAdapter
{
    private final String name[];
//    private final int images[];
    Context context;

    public GridAdapter(Context context,String[] name) {
        this.context = context;
        this.name = name;
    //    this.images = images;
    }

    @Override
    public int getCount()
    {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.singleframe,null);

        TextView tv=(TextView)view.findViewById(R.id.textdata);


        tv.setText(name[position]);

        return view;
    }
}