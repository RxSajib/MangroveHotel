package com.hotel.mangrovehotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Regastation_Activity extends AppCompatActivity {

    private EditText email, password, rpassword;
    private Button registerbutton, alreadyhaveaccount;
    private FirebaseAuth Mauth;
    private ProgressDialog Mprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regastation_);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        Mprogress = new ProgressDialog(Regastation_Activity.this);
        Mauth = FirebaseAuth.getInstance();
        email = findViewById(R.id.REmailID);
        password = findViewById(R.id.RPasswordID);
        rpassword = findViewById(R.id.RCPasswordID);

        registerbutton = findViewById(R.id.RLoginButtonID);
        alreadyhaveaccount = findViewById(R.id.RRegisterButtonID);

        alreadyhaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        email = findViewById(R.id.REmailID);
        password = findViewById(R.id.RPasswordID);
        rpassword = findViewById(R.id.RCPasswordID);

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailtext = email.getText().toString();
                String passwordtext = password.getText().toString();
                String rpasswordtxet = rpassword.getText().toString();

                if (emailtext.isEmpty()) {
                    email.setError("Email require");
                } else if (passwordtext.isEmpty()) {
                    password.setError("Password require");
                } else if (rpasswordtxet.isEmpty()) {
                    rpassword.setError("Password require");
                } else if (!passwordtext.equals(rpasswordtxet)) {
                    password.setError("Password didn't match");
                    rpassword.setError("Password didn't match");
                } else {

                    Mprogress.setTitle("Please wait");
                    Mprogress.setMessage("We are creating your account soon");
                    Mprogress.setCanceledOnTouchOutside(false);
                    Mprogress.show();


                    Mauth.createUserWithEmailAndPassword(emailtext, passwordtext)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        Mprogress.dismiss();
                                        Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(getApplicationContext(), "you are done", Toast.LENGTH_LONG).show();
                                    } else {
                                        Mprogress.dismiss();

                                        String errormessage = task.getException().getMessage().toString();
                                        Toast.makeText(getApplicationContext(), errormessage, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            String error = e.getMessage().toString();
                            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onStart() {

        FirebaseUser Muser = Mauth.getCurrentUser();
        if (Muser != null) {
            Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        super.onStart();
    }
}
