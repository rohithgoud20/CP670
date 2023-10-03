package com.example.androidassignments;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button Loginbutton;
    private EditText emailid;

    private SharedPreferences SP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailid=findViewById(R.id.editTextText);
        Loginbutton=findViewById(R.id.button3);
        SP=getSharedPreferences("application",Context.MODE_PRIVATE);
        String last=SP.getString("DefaultEmail","email@domain.com");
        emailid.setText(last);
        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredmail=emailid.getText().toString();
                SharedPreferences.Editor editor=SP.edit();
                editor.putString("DefaultEmail",enteredmail);
                editor.apply();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Loginbutton.setOnClickListener(view -> {
            EditText password = findViewById(R.id.editTextText2);
            String enteredEmail = emailid.getText().toString();
            String emailpattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";

            if(!(emailid.getText().toString().trim().matches(emailpattern))){
                InvalidEmail();
            }else if(password.getText().toString().matches("")){
                InvalidPassword();
            }else{
                saveEmailToSharedPreferences(enteredEmail);

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Log.i("LoginActivity","onCreate called");

        }

    private void saveEmailToSharedPreferences(String enteredEmail) {
    }

    private void InvalidPassword() {
        String message = getString(R.string.toast_message_invalid_password);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();



    }

    private void InvalidEmail() {

        String message = getString(R.string.toast_message_invalid_email);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    ;

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LoginActivity","onResume called");
        SharedPreferences SP2=getSharedPreferences("application",MODE_PRIVATE);
        String string= SP2.getString("DefaultEmail","email@domain.com");
        emailid.setText(string);
    }

    @Override
    protected void onStart() {

        super.onStart();
        Log.i("LoginActivity","onStart called");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LoginActivity","onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LoginActivity","onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LoginActivity","onDestory called");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i("LoginActivity","onSaveInstanceState called");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("LoginActivity","onRestoreInstanceState called");

    }
}