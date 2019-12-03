package com.hotel.mangrovehotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class ChatActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton sendbutton;
    private EditText inputmessage;
    private FirebaseAuth Mauth;
    private DatabaseReference Roodref;
    private String SenderUID, ReciverUserID;
    private RecyclerView messageview;
    private List<MessageHolder> messageHolderslis = new ArrayList<>();
    private MessageAdapter messageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        Mauth = FirebaseAuth.getInstance();
        Roodref = FirebaseDatabase.getInstance().getReference();
        SenderUID = Mauth.getCurrentUser().getUid();
        ReciverUserID = "EJe1Jn3lSjZbRbsHpGMX1tC7XNe2";

        toolbar = findViewById(R.id.ChatToolbarID);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        getSupportActionBar().setTitle("Help Line");

        sendbutton = findViewById(R.id.SendButtonID);
        inputmessage = findViewById(R.id.InputMessageID);

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String MessageText = inputmessage.getText().toString();

                if (MessageText.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "type anythings", Toast.LENGTH_LONG).show();
                } else {

                    sendingmessage(MessageText);

                }
            }
        });


        messageAdapter = new MessageAdapter(messageHolderslis);

        messageview = findViewById(R.id.MessageViewID);
        messageview.setHasFixedSize(true);
        messageview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        messageview.setAdapter(messageAdapter);
        DisplayMessage();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void DisplayMessage() {

        Roodref.child("Messages").child(SenderUID).child(ReciverUserID)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        MessageHolder messageHolder = dataSnapshot.getValue(MessageHolder.class);
                        messageHolderslis.add(messageHolder);
                        messageAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


    }

    private void sendingmessage(String messagetext) {

        String message_senderRef = "Messages/" + SenderUID + "/" + ReciverUserID;
        String message_reciverRef = "Messages/" + ReciverUserID + "/" + ReciverUserID;

        DatabaseReference user_message_key = Roodref.child("Message").child(SenderUID).child(ReciverUserID)
                .push();

        String message_pushID = user_message_key.getKey();

        ///date
        Calendar calendardate = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
        String dateget = simpleDateFormat.format(calendardate.getTime());

        Calendar caltesttime = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy (HH:mm:a)", Locale.getDefault());
        String caltime = sdf.format(caltesttime.getTime());

        TimeZone tz = TimeZone.getTimeZone("GMT+05:30");
        Calendar c = Calendar.getInstance(tz);
        String time = String.format("%02d", c.get(Calendar.HOUR_OF_DAY)) + ":" + String.format("%02d", c.get(Calendar.MINUTE)) + ":" + String.format("%02d", c.get(Calendar.SECOND)) + ":" + String.format("%03d", c.get(Calendar.MILLISECOND));


        ///time
        Calendar calendartime = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm a", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
        String timeget = simpleDateFormat1.format(calendartime.getTime());


        ///reciver time
        // Calendar calendartimekoria = Calendar.getInstance();
        // SimpleDateFormat simpleDateFormatkoria = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.CANADA);
        //String koriatime = simpleDateFormatkoria.format(calendartimekoria.getTime());

        Date today = new Date();
        DateFormat df = new SimpleDateFormat("hh:mm a");
        df.setTimeZone(TimeZone.getTimeZone("Asia/Dhaka"));
        String IST = df.format(today);


        Toast.makeText(getApplicationContext(), "koria_time " + IST, Toast.LENGTH_LONG).show();
        ///reciver time

        ///// send notifaction

        ///// send notifaction


        Map messagetext_body = new HashMap();
        messagetext_body.put("message", messagetext);
        messagetext_body.put("date", dateget);
        messagetext_body.put("time", timeget);
        messagetext_body.put("type", "text");
        messagetext_body.put("from", SenderUID);

        ///
        messagetext_body.put("to", ReciverUserID);
        messagetext_body.put("message_PushID", message_pushID);

        TelephonyManager tm = (TelephonyManager) getSystemService(getApplication().TELEPHONY_SERVICE);
        String countryCode = tm.getSimCountryIso();
        Toast.makeText(getApplicationContext(), countryCode, Toast.LENGTH_LONG).show();

        ///testing
        messagetext_body.put("sendertime", caltime);


        // messagetext_body.put("update_datefortesting", dateupdate);


        Map messagebody_details = new HashMap();
        messagebody_details.put(message_senderRef + "/" + message_pushID, messagetext_body);
        messagebody_details.put(message_reciverRef + "/" + message_pushID, messagetext_body);


        Roodref.updateChildren(messagebody_details).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()) {

                } else {

                    String error = task.getException().getMessage().toString();
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
