package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    private ListView listView;
    private EditText messageEditText;
    private Button sendButton;
    private ArrayList<String> chatMessages;
    private ChatAdapter CA;
    private static final String ACTIVITY_NAME = "ChatWindow";
    private ChatDatabaseHelper chatDatabaseHelper;
    private ArrayList<String> messages;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        listView = findViewById(R.id.listview);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        chatMessages = new ArrayList<>();
        CA = new ChatAdapter(this, android.R.layout.simple_list_item_1, chatMessages);
        listView.setAdapter(CA);

        messages = new ArrayList<>();
        chatDatabaseHelper = new ChatDatabaseHelper(this);
        SQLiteDatabase db = chatDatabaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ChatDatabaseHelper.TABLE_NAME, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int columnIndex = cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE);
            if (columnIndex >= 0) {
                String message = cursor.getString(columnIndex);
                Log.i(ACTIVITY_NAME, "Retrieved Message: " + message);
                chatMessages.add(message);
            }
            cursor.moveToNext();
        }
        Log.i(ACTIVITY_NAME, "Cursor's column count = " + cursor.getColumnCount());


        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.i(ACTIVITY_NAME, "Column Name: " + cursor.getColumnName(i));
        }


        cursor.close();
        CA.notifyDataSetChanged();



        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMessage = messageEditText.getText().toString();
                chatMessages.add(newMessage);
                CA.notifyDataSetChanged();
                messageEditText.setText("");


                SQLiteDatabase db = chatDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(ChatDatabaseHelper.KEY_MESSAGE, newMessage);
                db.insert(ChatDatabaseHelper.TABLE_NAME, null, values);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        chatDatabaseHelper.close();
    }


    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx, int simple_list_item_1, ArrayList<String> chatMessages) {
            super(ctx, 0,chatMessages);

        }
        public int getCount() {
            return chatMessages.size();
        }
        public String getItem(int position) {
            return chatMessages.get(position);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result;

            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }

            TextView message =result.findViewById(R.id.message_text);
            message.setText(getItem(position));

            return result;
        }


    }

}
