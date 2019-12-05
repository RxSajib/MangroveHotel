package com.hotel.mangrovehotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.InputDevice;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class LoginActivity extends AppCompatActivity {

    private EditText emailfilds, passwordfild;
    private Button loginbutton, registerbutton;
    private FirebaseAuth Mauth;
    private ProgressDialog Mprogress;
    private ImageView adminphoto;
    private Uri imageurls = null;

    private ImageView google;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Mprogress = new ProgressDialog(LoginActivity.this);
        Mauth = FirebaseAuth.getInstance();
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        google = findViewById(R.id.GoogleSiginID);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startGoogleSigin();
            }
        });

        adminphoto = findViewById(R.id.AdminPicture);
        adminphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin_LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        emailfilds = findViewById(R.id.EmailID);
        passwordfild = findViewById(R.id.PasswordID);
        loginbutton = findViewById(R.id.LoginButtonID);
        registerbutton = findViewById(R.id.RegisterButtonID);


        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Regastation_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailtext = emailfilds.getText().toString();
                String passwordtext = passwordfild.getText().toString();

                if (emailtext.isEmpty()) {

                    emailfilds.setError("Email require");
                } else if (passwordtext.isEmpty()) {
                    passwordfild.setError("Password require");
                } else {

                    Mprogress.setTitle("Please wait ");
                    Mprogress.setMessage("We are collecting your email and password");
                    Mprogress.setCanceledOnTouchOutside(false);
                    Mprogress.show();
                    Mauth.signInWithEmailAndPassword(emailtext, passwordtext)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        Mprogress.dismiss();
                                        Toast.makeText(getApplicationContext(), "you are done", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(LoginActivity.this, Home_Activity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Mprogress.dismiss();
                                        String errormessage = task.getException().getMessage().toString();
                                        Toast.makeText(getApplicationContext(), errormessage, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Mprogress.dismiss();
                            String errormessage = e.getMessage().toString();
                            Toast.makeText(getApplicationContext(), errormessage, Toast.LENGTH_LONG).show();
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
            Intent intent = new Intent(LoginActivity.this, Home_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        super.onStart();
    }


    private void startGoogleSigin() {


    }
}
