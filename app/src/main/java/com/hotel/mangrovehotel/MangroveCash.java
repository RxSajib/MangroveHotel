package com.hotel.mangrovehotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MangroveCash extends AppCompatActivity {

    private DatabaseReference MuserDatabase;
    private FirebaseAuth Mauth;
    private String CurrentUserD;
    private TextView currentname;
    private String ClickButton = "";
    private Button bookbutton;
    private TextView usernameString;
    private String username_textbellow;


    private Button dayone, daytwo, daythree, dayfour, dayfibe, daysix, dayseven, dayeight, daynine, dayten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangrove_cash);

        bookbutton = findViewById(R.id.BookButtonID);
        MuserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        Mauth = FirebaseAuth.getInstance();
        CurrentUserD = Mauth.getCurrentUser().getUid();
        currentname = findViewById(R.id.usernameID);

        usernameString = findViewById(R.id.uSERnAMEid);
        username_textbellow = usernameString.getText().toString();
        dayone = findViewById(R.id.oneID);
        daytwo = findViewById(R.id.twoID);
        daythree = findViewById(R.id.threeID);
        dayfour = findViewById(R.id.fourID);
        dayfibe = findViewById(R.id.fiveID);
        daysix = findViewById(R.id.sixID);
        dayseven = findViewById(R.id.sevenID);
        dayeight = findViewById(R.id.eightID);
        daynine = findViewById(R.id.nineID);
        dayten = findViewById(R.id.tenID);

        dayone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClickButton = "one";
                dayone.setBackgroundResource(R.drawable.click_background);

                daytwo.setBackgroundResource(R.drawable.daybutton_design);
                daythree.setBackgroundResource(R.drawable.daybutton_design);
                dayfour.setBackgroundResource(R.drawable.daybutton_design);
                dayfibe.setBackgroundResource(R.drawable.daybutton_design);
                daysix.setBackgroundResource(R.drawable.daybutton_design);
                dayseven.setBackgroundResource(R.drawable.daybutton_design);
                dayeight.setBackgroundResource(R.drawable.daybutton_design);
                daynine.setBackgroundResource(R.drawable.daybutton_design);
                dayten.setBackgroundResource(R.drawable.daybutton_design);
                onClikcEvant(ClickButton);
            }
        });

        daytwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickButton = "two";
                daytwo.setBackgroundResource(R.drawable.click_background);


                dayone.setBackgroundResource(R.drawable.daybutton_design);
                daythree.setBackgroundResource(R.drawable.daybutton_design);
                dayfour.setBackgroundResource(R.drawable.daybutton_design);
                dayfibe.setBackgroundResource(R.drawable.daybutton_design);
                daysix.setBackgroundResource(R.drawable.daybutton_design);
                dayseven.setBackgroundResource(R.drawable.daybutton_design);
                dayeight.setBackgroundResource(R.drawable.daybutton_design);
                daynine.setBackgroundResource(R.drawable.daybutton_design);
                dayten.setBackgroundResource(R.drawable.daybutton_design);
                onClikcEvant(ClickButton);
            }
        });

        daythree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickButton = "three";
                daythree.setBackgroundResource(R.drawable.click_background);

                dayone.setBackgroundResource(R.drawable.daybutton_design);
                daytwo.setBackgroundResource(R.drawable.daybutton_design);
                dayfour.setBackgroundResource(R.drawable.daybutton_design);
                dayfibe.setBackgroundResource(R.drawable.daybutton_design);
                daysix.setBackgroundResource(R.drawable.daybutton_design);
                dayseven.setBackgroundResource(R.drawable.daybutton_design);
                dayeight.setBackgroundResource(R.drawable.daybutton_design);
                daynine.setBackgroundResource(R.drawable.daybutton_design);
                dayten.setBackgroundResource(R.drawable.daybutton_design);
                onClikcEvant(ClickButton);
            }
        });

        dayfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickButton = "four";
                dayfour.setBackgroundResource(R.drawable.click_background);

                dayone.setBackgroundResource(R.drawable.daybutton_design);
                daytwo.setBackgroundResource(R.drawable.daybutton_design);
                daythree.setBackgroundResource(R.drawable.daybutton_design);
                dayfibe.setBackgroundResource(R.drawable.daybutton_design);
                daysix.setBackgroundResource(R.drawable.daybutton_design);
                dayseven.setBackgroundResource(R.drawable.daybutton_design);
                dayeight.setBackgroundResource(R.drawable.daybutton_design);
                daynine.setBackgroundResource(R.drawable.daybutton_design);
                dayten.setBackgroundResource(R.drawable.daybutton_design);
                onClikcEvant(ClickButton);
            }
        });

        dayfibe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickButton = "five";
                dayfibe.setBackgroundResource(R.drawable.click_background);

                dayone.setBackgroundResource(R.drawable.daybutton_design);
                daytwo.setBackgroundResource(R.drawable.daybutton_design);
                daythree.setBackgroundResource(R.drawable.daybutton_design);
                dayfour.setBackgroundResource(R.drawable.daybutton_design);
                daysix.setBackgroundResource(R.drawable.daybutton_design);
                dayseven.setBackgroundResource(R.drawable.daybutton_design);
                dayeight.setBackgroundResource(R.drawable.daybutton_design);
                daynine.setBackgroundResource(R.drawable.daybutton_design);
                dayten.setBackgroundResource(R.drawable.daybutton_design);
                onClikcEvant(ClickButton);
            }
        });

        daysix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickButton = "six";
                daysix.setBackgroundResource(R.drawable.click_background);

                dayone.setBackgroundResource(R.drawable.daybutton_design);
                daytwo.setBackgroundResource(R.drawable.daybutton_design);
                daythree.setBackgroundResource(R.drawable.daybutton_design);
                dayfour.setBackgroundResource(R.drawable.daybutton_design);
                dayfibe.setBackgroundResource(R.drawable.daybutton_design);
                dayseven.setBackgroundResource(R.drawable.daybutton_design);
                dayeight.setBackgroundResource(R.drawable.daybutton_design);
                daynine.setBackgroundResource(R.drawable.daybutton_design);
                dayten.setBackgroundResource(R.drawable.daybutton_design);
                onClikcEvant(ClickButton);
            }
        });
        dayseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickButton = "seven";
                dayseven.setBackgroundResource(R.drawable.click_background);

                dayone.setBackgroundResource(R.drawable.daybutton_design);
                daytwo.setBackgroundResource(R.drawable.daybutton_design);
                daythree.setBackgroundResource(R.drawable.daybutton_design);
                dayfour.setBackgroundResource(R.drawable.daybutton_design);
                dayfibe.setBackgroundResource(R.drawable.daybutton_design);
                daysix.setBackgroundResource(R.drawable.daybutton_design);
                dayeight.setBackgroundResource(R.drawable.daybutton_design);

                daynine.setBackgroundResource(R.drawable.daybutton_design);
                dayten.setBackgroundResource(R.drawable.daybutton_design);
                onClikcEvant(ClickButton);
            }
        });

        dayeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickButton = "eight";
                dayeight.setBackgroundResource(R.drawable.click_background);


                dayone.setBackgroundResource(R.drawable.daybutton_design);
                daytwo.setBackgroundResource(R.drawable.daybutton_design);
                daythree.setBackgroundResource(R.drawable.daybutton_design);
                dayfour.setBackgroundResource(R.drawable.daybutton_design);
                dayfibe.setBackgroundResource(R.drawable.daybutton_design);
                daysix.setBackgroundResource(R.drawable.daybutton_design);
                dayseven.setBackgroundResource(R.drawable.daybutton_design);
                daynine.setBackgroundResource(R.drawable.daybutton_design);
                dayten.setBackgroundResource(R.drawable.daybutton_design);
                onClikcEvant(ClickButton);
            }
        });

        daynine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickButton = "nine";
                daynine.setBackgroundResource(R.drawable.click_background);

                dayone.setBackgroundResource(R.drawable.daybutton_design);
                daytwo.setBackgroundResource(R.drawable.daybutton_design);
                daythree.setBackgroundResource(R.drawable.daybutton_design);
                dayfour.setBackgroundResource(R.drawable.daybutton_design);
                dayfibe.setBackgroundResource(R.drawable.daybutton_design);
                daysix.setBackgroundResource(R.drawable.daybutton_design);
                dayseven.setBackgroundResource(R.drawable.daybutton_design);
                dayeight.setBackgroundResource(R.drawable.daybutton_design);
                dayten.setBackgroundResource(R.drawable.daybutton_design);


                onClikcEvant(ClickButton);
            }
        });
        dayten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickButton = "ten";
                dayten.setBackgroundResource(R.drawable.click_background);


                dayone.setBackgroundResource(R.drawable.daybutton_design);
                daytwo.setBackgroundResource(R.drawable.daybutton_design);
                daythree.setBackgroundResource(R.drawable.daybutton_design);
                dayfour.setBackgroundResource(R.drawable.daybutton_design);
                dayfibe.setBackgroundResource(R.drawable.daybutton_design);
                daysix.setBackgroundResource(R.drawable.daybutton_design);
                dayseven.setBackgroundResource(R.drawable.daybutton_design);
                dayeight.setBackgroundResource(R.drawable.daybutton_design);
                daynine.setBackgroundResource(R.drawable.daybutton_design);


                onClikcEvant(ClickButton);
            }
        });
        MuserDatabase.child(CurrentUserD).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    if (dataSnapshot.hasChild("username")) {
                        String usernameget = dataSnapshot.child("username").getValue().toString();
                        currentname.setText(usernameget);
                        usernameString.setText("** " + usernameget + " **");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void onClikcEvant(final String Action) {
        Toast.makeText(getApplicationContext(), Action, Toast.LENGTH_LONG).show();

        bookbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    bookbutton.setBackgroundResource(R.drawable.book_button);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    bookbutton.setBackgroundResource(R.drawable.book_nowdesign);
                }

                return false;
            }
        });
    }
}
