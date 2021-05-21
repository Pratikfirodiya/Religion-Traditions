package com.example.myapplication.DrawerActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.AuthenticationActivities.SignupActivity;
import com.example.myapplication.MainActivity;

import com.example.myapplication.Navigationdrawer.DrawerActivity;
import com.example.myapplication.R;
import com.example.myapplication.SettingsActivity;
import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends DrawerActivity implements View.OnClickListener {
Button setting,logout;
    ImageButton imageButton;
    private ActionBarDrawerToggle t;
    SharedPreferences pref,pref2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
        LayoutInflater inflater=LayoutInflater.from(this);
        View v=inflater.inflate(R.layout.activity_profile,null,false);
        drawer.addView(v,0);
        pref2 = getApplicationContext().getSharedPreferences("credentials", 0);

        if(pref2.contains("email")&&pref2.contains("password"))
        {
            currentactivity();
        }
        else
        {

            setContentView(R.layout.createaccount);
            callsignupactivity();
        }


    }

    private void currentactivity() {
        pref = getApplicationContext().getSharedPreferences("Religionset", 0); //
        pref2 = getApplicationContext().getSharedPreferences("credentials", 0); //
        imageButton =  findViewById(R.id.profilepic);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setting=findViewById(R.id.Profilesetting);
        logout=findViewById(R.id.ProfileLogout);
        logout.setOnClickListener(this);
        setting.setOnClickListener(this);
        imageButton.setOnClickListener(this);
    }

    public void callsignupactivity() {
        getSupportActionBar().setTitle("Create Account");
        TextView textView;
        textView=findViewById(R.id.createacctext);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        Toast.makeText(getApplicationContext(),"sfsr",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        imageButton.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                imageButton.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }
    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.Profilesetting)
        {
            Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.profilepic)
        {
           selectImage(ProfileActivity.this);
        }
        if(v.getId()==R.id.ProfileLogout)
        {

//            SharedPreferences.Editor editor = pref.edit();
//            editor.clear();
//            editor.commit();

            SharedPreferences.Editor editor2 = pref2.edit();
            editor2.clear();
            editor2.commit();
            if(pref2.contains("email")&&pref2.contains("password"))
            {
                Toast.makeText(ProfileActivity.this,"right",Toast.LENGTH_LONG).show();
            }
        }
    }
}