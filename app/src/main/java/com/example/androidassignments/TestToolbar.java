package com.example.androidassignments;

import static android.app.ProgressDialog.show;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.androidassignments.R.id;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.androidassignments.databinding.ActivityTestToolbarBinding;

public class TestToolbar extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityTestToolbarBinding binding;
    private String customMessage = "";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Hi, how are you?", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        switch (id) {
//            case R.id.action_one:
//                Log.d("Toolbar", "Option 1 selected");
//                break;
//            case R.id.action_two:
//                Log.d("Toolbar","Option 2 selected");
//                break;
//            case R.id.action_three:
//                Log.d("Toolbar","Option 3 selected");
//        }

//        return super.onOptionsItemSelected(item);
        if (id == R.id.action_one) {
            if (!customMessage.isEmpty()) {
                Snackbar.make(findViewById(android.R.id.content), customMessage, Snackbar.LENGTH_SHORT).show();
                Log.d("Toolbar", "Snackbar for Item 1 with custom message");
            } else {
                Snackbar.make(findViewById(android.R.id.content), "you selected Item 1", Snackbar.LENGTH_SHORT).show();
                Log.d("Toolbar", "Snackbar for Item 1 with default message");
            }
        } else if (id == R.id.action_two) {
            Snackbar.make(findViewById(R.id.content), "You selected item 2", Snackbar.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.dialog_title);
            builder.setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    finish();
                }
            });
            builder.setNegativeButton(R.string.dialog_negative_button, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            Log.d("Toolbar", "item 2 selected");

        } else if (id == R.id.action_three) {
            Snackbar.make(findViewById(R.id.content), "You selected item 3", Snackbar.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_dialog_layout, null);
            builder.setView(dialogView);

            EditText editTextNewMessage = dialogView.findViewById(R.id.editTextNewMessage);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    customMessage = editTextNewMessage.getText().toString(); // Store the entered message
                    Log.d("Toolbar", "Custom message stored: " + customMessage);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // User cancelled the dialog, do nothing
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
            Log.d("Toolbar", "item 3 selected");
            } else if (id == R.id.about) {
            Snackbar.make(findViewById(R.id.content), "You selected item 4", Snackbar.LENGTH_SHORT).show();
            Toast.makeText(this, R.string.aboutm, Toast.LENGTH_SHORT).show();
            Log.d("Toolbar", "item 4 selected");
        }

        return true;
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityTestToolbarBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.toolbar);
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_test_toolbar);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
//            }
//        });
//    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_test_toolbar);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
}