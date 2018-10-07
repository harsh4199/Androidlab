package com.example.mistr.androidlab;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends Activity {

    //ArrayList to hold the messages of the chat.
    private ArrayList<String> msgs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        ListView listView = (ListView) findViewById(R.id.listView);
        final ChatAdapter messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);


        Button button = (Button) findViewById(R.id.sendButton);
        final EditText editText = (EditText) findViewById(R.id.editText);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String aSingleMessage = editText.getText().toString();

                /*
                todo: the trim() VVVVVVV doesn't get rid of the carriage return!
                 */
                int messageCharLength = aSingleMessage.trim().length();


                if (TextUtils.isEmpty(aSingleMessage)) {
                    editText.setError("Empty message ignored");
                } else {
                    /*
                    add the msg to the ArrayList
                    set the hint to the number of msg
                    todo: if the ArrayList is empty, invite the user to type something in!
                     */

                    getMsgs().add(aSingleMessage);
                    editText.setText("");
                    editText.setHint("So far " + getMsgs().size() + " messages");
                    messageAdapter.notifyDataSetChanged();

                }
            }
        });
    }

    public ArrayList<String> getMsgs() {
        return msgs;
    }

    public void setMsgs(ArrayList<String> msgs) {
        this.msgs = msgs;
    }



    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context context) {
            super(context, 0);
        }

        public int getCount() {
            return getMsgs().size();
        }

        public String getItem(int position) {
            return getMsgs().get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            }


            TextView message = (TextView) result.findViewById(R.id.message_text);  //this is the msg from the chat_row_incoming/outing
            message.setText(getItem(position));
            return result;

        }


    }//end class ChatAdapter


}//end class ChatWindow

