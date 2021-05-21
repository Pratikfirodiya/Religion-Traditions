package com.example.myapplication.Navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.DrawerActivities.AddActivity;
import com.example.myapplication.DrawerActivities.FavouritesActivity;
import com.example.myapplication.DrawerActivities.MycontributionActivity;
import com.example.myapplication.DrawerActivities.ProfileActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ValidateArticlesActivity;
import com.google.android.material.navigation.NavigationView;

public class DrawerActivity extends AppCompatActivity {

   public DrawerLayout drawer;
   public ActionBarDrawerToggle t;
    public NavigationView nv;
    SharedPreferences pref2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        drawer = (DrawerLayout)findViewById(R.id.activity_drawer);
        t = new ActionBarDrawerToggle(this, drawer,R.string.Open, R.string.Close);

        drawer.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nvview);
        final Menu menu=nv.getMenu();
//        MenuItem itemSearch = menu.findItem(R.id.validatearticles);
//        pref2 = getApplicationContext().getSharedPreferences("credentials", 0);
//
//        if(pref2.getString("email","").equals("pratik@gmail.com")&&pref2.getString("password","").equals("pratik"))
//        {
//            itemSearch.setVisible(true);
//        }
//        else
//        {
//            itemSearch.setVisible(false);
//        }
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.Home:

                      //  drawer.closeDrawer(Gravity.LEFT,true);
                            Intent home=new Intent(getApplicationContext(),MainActivity.class);
                     startActivity(home);
                     break;



//                    case R.id.Add:
//                        Intent add=new Intent(getApplicationContext(), AddActivity.class);
//                        startActivity(add);
//                        break;
                    case R.id.Mycontribution:
                        Intent mycontri=new Intent(getApplicationContext(), MycontributionActivity.class);
                        startActivity(mycontri);
                        break;
                    case R.id.Profile:
                        Intent profile1=new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(profile1);
                        break;
                    case R.id.Fav:

                  Intent profile2=new Intent(getApplicationContext(), FavouritesActivity.class);
                  startActivity(profile2);
                          break;
//                    case R.id.validatearticles:
//                  Intent articles=new Intent(getApplicationContext(), ValidateArticlesActivity.class);
//                  startActivity(articles);
//                        break;
                    default:
                        return true;
                }


                return true;

            }
        });


    }
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))

            return true;

        return super.onOptionsItemSelected(item);
    }
}