package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        listView = findViewById(R.id.listview);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        chatMessages = new ArrayList<>();
        CA= new ChatAdapter(this,android.R.layout.simple_list_item_1,chatMessages);
        listView.setAdapter(CA);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textInput = findViewById(R.id.messageEditText);
                String message = messageEditText.getText().toString().trim();
                if (!message.isEmpty()) {
                    // Add the new message to your data source (e.g., ArrayList)
                    CA.add(message);

                    // Notify the adapter that the data has changed
                    CA.notifyDataSetChanged();

                    // Clear the EditText for a new message
                    messageEditText.setText("");
                }


            }
        });
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
