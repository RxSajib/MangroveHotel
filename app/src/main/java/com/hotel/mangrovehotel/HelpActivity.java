package com.hotel.mangrovehotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInstaller;
import android.media.MediaCas;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.textservice.SpellCheckerService;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.hotel.mangrovehotel.malsender.Mailsender;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static javax.mail.internet.InternetAddress.parse;

public class HelpActivity extends AppCompatActivity {

    private EditText number, email;
    private ImageButton callbutton;

    private Session session = null;
    private ProgressDialog progressDialog = null;
    private Context context;
    private String useremail, numbert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        number = findViewById(R.id.PhoneNumberID);
        email = findViewById(R.id.EmailIDID);
        callbutton = findViewById(R.id.CallButtobID);

        context = this;


        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String numbertext = number.getText().toString().trim();
                String emailtext = email.getText().toString().trim();

                if (numbertext.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Number require", Toast.LENGTH_LONG).show();
                } else if (emailtext.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email Require", Toast.LENGTH_LONG).show();
                } else {

                }
            }
        });


    }


}
