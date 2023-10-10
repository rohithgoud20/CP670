package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button but;
    private Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but=findViewById(R.id.button2);
        b2=findViewById(R.id.button);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent=new Intent(MainActivity.this, ChatWindow.class);
                startActivity(intent);
                Log.i("MainActivity","User clicked Start Chat");
            }
        });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 10);
                startActivity(intent);
            }
        });

        Log.i("MainActivity","onCreate called");
    }
    public void openLoginActivity(){
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.i("MainActivity", "onResume called");
    }

    @Override
    protected void onStart() {

        super.onStart();
        Log.i("MainActivity", "onStart called");
    }

    @Override
    protected void onPause() {

        super.onPause();
        Log.i("MainActivity", "onPause called");
    }

    @Override
    protected void onStop() {

        super.onStop();
        Log.i("MainActivity", "onStop called");
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        Log.i("MainActivity", "onDestroy called");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i("MainActivity", "onSaveInstanceState called");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("MainActivity", "onRestoreInstanceState called");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10) {
            String MainActivity;
            Log.i("MainActivity", "Returned to MainActivity.onActivityResult");
            if (resultCode == RESULT_OK) {

                String messagePassed = data.getStringExtra("Response");
                Toast.makeText(this, "ListItemsActivity passed: " + messagePassed, Toast.LENGTH_SHORT).show();
            }

        }
    }
}