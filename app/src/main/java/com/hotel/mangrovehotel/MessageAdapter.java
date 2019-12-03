package com.hotel.mangrovehotel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {


    private List<MessageHolder> usermessageList;
    private FirebaseAuth Mauth;
    private DatabaseReference usersdatabaseref;
    private Context context;


    public MessageAdapter(List<MessageHolder> usermessageList) {
        this.usermessageList = usermessageList;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout senderlayout, reciverlayout;
        private TextView sendertext, recivertext;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            senderlayout = itemView.findViewById(R.id.SenderLayout);
            reciverlayout = itemView.findViewById(R.id.ReciverLayout);

            sendertext = itemView.findViewById(R.id.SenderTextID);
            recivertext = itemView.findViewById(R.id.ReciverTextID);
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_placeholder, parent, false);
        Mauth = FirebaseAuth.getInstance();
        return new MessageViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {


        final String messagesenderID = Mauth.getCurrentUser().getUid();
        final MessageHolder usermessagelist = usermessageList.get(position);
        String fromuserId = usermessagelist.getFrom();
        String frommesstype = usermessagelist.getType();

        usersdatabaseref = FirebaseDatabase.getInstance().getReference().child("Users").child(fromuserId);
        usersdatabaseref.keepSynced(true);


        holder.recivertext.setVisibility(View.GONE);
        holder.reciverlayout.setVisibility(View.GONE);
        holder.sendertext.setVisibility(View.GONE);
        holder.senderlayout.setVisibility(View.GONE);


        if (frommesstype.equals("text")) {

            if (fromuserId.equals(messagesenderID)) {
                holder.senderlayout.setVisibility(View.VISIBLE);
                holder.sendertext.setVisibility(View.VISIBLE);

                holder.sendertext.setText(usermessagelist.getMessage());

            } else {

                holder.reciverlayout.setVisibility(View.VISIBLE);
                holder.recivertext.setVisibility(View.VISIBLE);

                holder.recivertext.setText(usermessagelist.getMessage());
            }
        }
    }

    @Override
    public int getItemCount() {
        return usermessageList.size();
    }


}

