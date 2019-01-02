package android.pronixits.com.mapadviceapplication;

import android.content.Intent;
import android.pronixits.com.mapadviceapplication.adapter.MessageAdapter;
import android.pronixits.com.mapadviceapplication.models.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    EditText message;
    ImageButton send;

    FirebaseUser user;
    ListView listView;
    List<Message> messages = new ArrayList<>();
    String currentuserid;
    final String userid = "TZLMGL79u3WDbE1EAsFpui7PUPW2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        message = (EditText)findViewById(R.id.text_send);
        send = (ImageButton)findViewById(R.id.send_btn);
        listView = (ListView)findViewById(R.id.l_view);


        user = FirebaseAuth.getInstance().getCurrentUser();
        currentuserid = user.getUid();

        onopenchat();



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msg = message.getText().toString();
                if(!msg.equals(""))
                {
                    sendMessage(user.getUid(),userid,msg);
                }
                else
                {
                    Toast.makeText(ChatActivity.this, "You can't send empty message", Toast.LENGTH_SHORT).show();
                }
                message.setText("");
            }
        });

    }

    private void onopenchat() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messages.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    //Toast.makeText(ChatActivity.this, dataSnapshot1.getValue(Message.class).getMessage(), Toast.LENGTH_SHORT).show();


                    Message messagecustom  = dataSnapshot1.getValue(Message.class);

                    messagecustom.getDate();
                    messagecustom.getSender();
                    messagecustom.getReceiver();
                    messagecustom.getMessage();



                    if((messagecustom.getSender().equals(currentuserid) && messagecustom.getReceiver().equals(userid) ) || (messagecustom.getSender().equals(userid) && messagecustom.getReceiver().equals(currentuserid)))
                    {
                        messages.add(messagecustom);
                    }


                }

                MessageAdapter messageAdapter = new MessageAdapter(getApplicationContext(),messages);
                listView.setAdapter(messageAdapter);


                //Toast.makeText(ChatActivity.this, messages.size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Intent intent = new Intent(ChatActivity.this,MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void sendMessage(String sender,String receiver,String message)
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Date mydate = Calendar.getInstance().getTime();

        Message message1 = new Message();

        message1.setDate(mydate.toString());
        message1.setMessage(message);
        message1.setReceiver(receiver);
        message1.setSender(sender);

//        HashMap<String,Object> hashMap = new HashMap<>();
//
//        hashMap.put("sender",sender);
//        hashMap.put("receiver",receiver);
//        hashMap.put("message",message);
//        hashMap.put("time", mydate);

        databaseReference.child("Chats").push().setValue(message1);

    }


}
