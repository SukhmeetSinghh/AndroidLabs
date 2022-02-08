package com.cst2335.sing1539;
import androidx.appcompat.app.AlertDialog;
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
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {


    private EditText edtMessage;
    ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        List<Message> messages = new ArrayList<>();

        Button sendButton = findViewById(R.id.button_Send);
        Button receiveButton = findViewById(R.id.buttonReceive);
        edtMessage= findViewById(R.id.edit_message);
        ListView listview = findViewById(R.id.listview);

        adapter= new ChatAdapter(ChatRoomActivity.this, messages);
        listview.setAdapter(adapter);

        sendButton.setOnClickListener(v -> {

            String msg =edtMessage.getText().toString();
            Message message = new Message();
            message.message=msg;
            message.id=11;
            messages.add(message);
            adapter.notifyDataSetChanged();
            edtMessage.setText("");
        });


        receiveButton.setOnClickListener(v -> {

            String msg =edtMessage.getText().toString();
            Message message = new Message();
            message.message=msg;
            message.id=12;
            messages.add(message);
            adapter.notifyDataSetChanged();
            edtMessage.setText("");
        });

        listview.setOnItemLongClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoomActivity.this);
            builder.setTitle("Would you like to delete the message")
                    .setMessage("The selected row is: "+position)
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, which) -> {
                        messages.remove(position); //remove the message
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", (dialog, which) -> {

                    });
            AlertDialog dialog  = builder.create();
            dialog.show();
            return true;
        });


    }

    private static class Message{
        String message;
        int id;
    }

    public static class ChatAdapter extends ArrayAdapter<Message> {
        private final Context context;
        private final List<Message> messageList;

        public ChatAdapter(Context context,List<Message> messages) {
            super(context, -1, messages);
            this.context = context;
            this.messageList = messages;
        }
        @Override
        public int getCount() {
            return messageList.size();
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public Message getItem(int position) {
            return messageList.get(position);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = null;
            Message message =messageList.get(position);
            if(message.id==11){
                rowView = inflater.inflate(R.layout.activity_send_layout, parent, false);
                TextView send_message = (TextView) rowView.findViewById(R.id.send_msg);
                send_message.setText(messageList.get(position).message);
            }

            if(message.id==12){
                rowView = inflater.inflate(R.layout.activity_recieve_layout, parent, false);
                TextView receive_message = (TextView) rowView.findViewById(R.id.receive_msg);
                receive_message.setText(messageList.get(position).message);
            }

            return rowView;
        }
    }
}