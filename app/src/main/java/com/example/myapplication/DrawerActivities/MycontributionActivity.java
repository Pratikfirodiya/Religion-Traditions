package com.example.myapplication.DrawerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;


import com.example.myapplication.DrawerActivities.MyContibutionFragment.ApprovedFragment;
import com.example.myapplication.DrawerActivities.MyContibutionFragment.PendingFragment;
import com.example.myapplication.DrawerActivities.MyContibutionFragment.RejectedFragment;
import com.example.myapplication.Navigationdrawer.DrawerActivity;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MycontributionActivity extends DrawerActivity implements View.OnClickListener {

    Toolbar toolbar;
     ViewPager viewPager;
     TabLayout tabLayout;
     ExtendedFloatingActionButton extendedFloatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        //setContentView(R.layout.activity_main);
        View v=inflater.inflate(R.layout.activity_mycontribution,null,false);
        drawer.addView(v,0);
//      toolbar = findViewById(R.id.toolbar);
//      toolbar.setTitle(getIntent().getStringExtra("festival"));
//      setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MyContribution");
//      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        extendedFloatingActionButton=findViewById(R.id.addarticles);
        extendedFloatingActionButton.setOnClickListener(this);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_outline_done_outline_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_outline_cancel_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_pending_24);

    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ApprovedFragment(), "Approved");
        adapter.addFrag(new RejectedFragment(), "Rejected");
        adapter.addFrag(new PendingFragment(),"Pending");
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.addarticles)
        {
            Intent add=new Intent(MycontributionActivity.this, AddActivity.class);
            startActivity(add);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}