package com.example.androidassignments;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE=10;
    private ImageButton im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Switch ST=findViewById(R.id.switch1);
        CheckBox CB=findViewById(R.id.checkBox);
        im=findViewById(R.id.imageButton);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to open the default photo application
                Intent takePictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                // Check if there is a camera app available to handle the intent
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });


        ST.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String m;
                int d;
                if(b){
                    m= getString(R.string.switch_on);;
                    d= Toast.LENGTH_SHORT;

                }
                else{
                    m=getString(R.string.switch_off);;
                    d= Toast.LENGTH_LONG;

                }
                Toast toast = Toast.makeText(getApplicationContext(), m, d);
                toast.show();

            }
        });
        CB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                    builder.setMessage(R.string.dialog_message) // Add a dialog message to strings.xml
                            .setTitle(R.string.dialog_title)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User clicked OK button
                                    String responseMessage =getString(R.string.response_1);
                                    Intent resultIntent = new Intent();
                                    resultIntent.putExtra("Response", responseMessage);
                                    setResult(Activity.RESULT_OK, resultIntent);
                                    finish();
                                    // Finish the activity
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            })
                            .show();
                }
            }
        });
        Log.i("ListItemActivity","onCreate called");
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            if (extras != null) {

                Bitmap imageBitmap = (Bitmap) extras.get("data");


                im.setImageBitmap(imageBitmap);
            }
        }
    }
    public void print(String m){Toast.makeText(this,m,Toast.LENGTH_SHORT).show();}

    private void openLoginActivity(){
        Intent intent= new Intent( this, LoginActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ListItemActivity","onResume called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ListItemActivity","onStart called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ListItemActivity","onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ListItemActivity","onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ListItemActivity","onStop called");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i("ListItemActivity","onSaveInstanceState called");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("ListItemActivity","onRestoreInstanceSate called");
    }
}