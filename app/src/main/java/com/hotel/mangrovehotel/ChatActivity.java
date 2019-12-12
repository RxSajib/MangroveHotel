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
    private String ReciverID;
    private String SenderID;
    private FirebaseAuth Mauth;
    private EditText messagetext;
    private ImageButton sendbitton;
    private DatabaseReference Roodref;
    private String CurrentTime;
    private String CurrentDate;
    private String Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Roodref = FirebaseDatabase.getInstance().getReference();
        Mauth = FirebaseAuth.getInstance();
        SenderID = Mauth.getCurrentUser().getUid();
        messagetext = findViewById(R.id.InputMessageID);
        ReciverID = "d6OszNdavEUTV9nEhD5AEyNpIaE2";
        sendbitton = findViewById(R.id.SendButtonID);
        toolbar = findViewById(R.id.ChatToolbarID);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        getSupportActionBar().setTitle("Help Desk");


        sendbitton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message = messagetext.getText().toString();

                if (Message.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Type any message", Toast.LENGTH_LONG).show();
                } else {
                    SendingMessage();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void SendingMessage() {

        String Message_senderRef = "Message/" + SenderID + "/" + ReciverID;
        String Message_reciverRef = "Message/" + ReciverID + "/" + SenderID;

        DatabaseReference usersmessagekey = Roodref.child("Message").child(SenderID).child(ReciverID)
                .push();

        String message_pushid = usersmessagekey.getKey();

        Calendar calendartime = Calendar.getInstance();
        SimpleDateFormat simpleDateFormattime = new SimpleDateFormat("dd-MMMM-yyyy");
        CurrentTime = simpleDateFormattime.format(calendartime.getTime());


        Calendar calendardate = Calendar.getInstance();
        SimpleDateFormat simpleDateFormatdate = new SimpleDateFormat("HH-mm");
        CurrentDate = simpleDateFormatdate.format(calendardate.getTime());

        Map messagetextbody = new HashMap();
        messagetextbody.put("message", Message);
        messagetextbody.put("time", CurrentTime);
        messagetextbody.put("date", CurrentDate);
        messagetextbody.put("type", "text");
        messagetextbody.put("from", SenderID);

        Map message_bodydetails = new HashMap();
        message_bodydetails.put(Message_senderRef + "/" + message_pushid, messagetextbody);
        message_bodydetails.put(Message_reciverRef + "/" + message_pushid, messagetextbody);

        Roodref.updateChildren(message_bodydetails)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "message sent", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
