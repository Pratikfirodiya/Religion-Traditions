package com.example.myapplication.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ValidationlistAdapter  extends ArrayAdapter {

    private List<String> typename=new ArrayList<>();
    private List<String> desc=new ArrayList<>();
    private List<String> userid=new ArrayList<>();
    private List<String> imageid=new ArrayList<>();


    private Activity context;

    public  ValidationlistAdapter(Activity context, List<String>typename,  List<String> desc, List<String> imageid) {
        super(context, R.layout.row, typename);
        this.context = context;
        this.typename= typename;
        this.desc = desc;
        this.imageid = imageid;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row, null, true);
        TextView textViewCountry = (TextView) row.findViewById(R.id.textViewtypename);
        TextView textViewCapital = (TextView) row.findViewById(R.id.desc);
        ImageView imageFlag = (ImageView) row.findViewById(R.id.imageurl);
        textViewCountry.setText(typename.get(position));
        textViewCapital.setText(desc.get(position));
        Glide.with(context)
                .load(imageid.get(position))
                .into(imageFlag);

        return  row;
    }
}
