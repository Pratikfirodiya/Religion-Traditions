package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.myapplication.Models.FavouritesModel;
import com.example.myapplication.Models.Modelview;
import com.example.myapplication.R;

import java.util.List;

public class FavouritesAdapter extends PagerAdapter {
    private List<FavouritesModel> models;
    private LayoutInflater layoutInflater;
    private Context context;
    public FavouritesAdapter(List<FavouritesModel> models, Context context) {
        this.models = models;
        this.context = context;
    }
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);

        ImageView imageView;
        TextView title, desc;
        ToggleButton b;
        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);
        b=view.findViewById(R.id.button_favorite);
        b.setChecked(true);


        Glide.with(context)
                .load(models.get(position).getImageUrl())
                .into(imageView);
        // imageView.setImageResource(models.get(position).getImage());
        desc.setText(models.get(position).getDesc());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Toast.makeText(context,"sdsdk"+ models.get(position).getImageUrl(),Toast.LENGTH_SHORT).show();
                //     Intent intent = new Intent(context, DetailActivity.class);
                //   intent.putExtra("param", models.get(position).getTitle());
                //  context.startActivity(intent);
                // finish();
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
