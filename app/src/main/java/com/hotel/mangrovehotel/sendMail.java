package com.hotel.mangrovehotel;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Properties;

import javax.mail.Session;

import static javax.mail.Session.getDefaultInstance;

public class sendMail extends AsyncTask<Void, Void, Void> {

    private Context context;
    private Session session;
    private String emailAddress, Subject, Message;
    private ProgressDialog progressDialog;


    public sendMail(Context context, String emailAddress, String subject, String message) {
        this.context = context;
        this.emailAddress = emailAddress;
        Subject = subject;
        Message = message;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocket.Factory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


        //session = Session.getDefaultInstance(props, get)

        return null;
    }


}
