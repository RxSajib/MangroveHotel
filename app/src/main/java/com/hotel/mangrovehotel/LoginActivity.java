package com.hotel.mangrovehotel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText emailfilds, passwordfild;
    private Button loginbutton, registerbutton;
    private FirebaseAuth Mauth;
    private ProgressDialog Mprogress;
    private Animation adminphoto;
    private Uri imageurls = null;
    private ImageView google;
    private Button booknow;
    private RelativeLayout instrambutton;
    private RelativeLayout gmailbutton;
    private GoogleApiClient mgooglesignClint;
    private static final String TAG = "LoginActivity";
    private GoogleApiClient mgoogleApiclint;
    private TextView discounttext;
    private Button tenoff;
    private Animation animation;


    private static final int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        discounttext = findViewById(R.id.TextViewID);
        gmailbutton = findViewById(R.id.GmailButtonID);
        tenoff = findViewById(R.id.SS);

        tenoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discounttext.setText("Get An Amazing OFFER When You Sign Up Today");
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.frombutton);
                discounttext.setAnimation(animation);

                Intent intent = new Intent(getApplicationContext(), BestDeals.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        instrambutton = findViewById(R.id.InstregramLoginID);


        booknow = findViewById(R.id.BookNOwButtonID);
        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookNowLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        Mprogress = new ProgressDialog(LoginActivity.this);
        Mauth = FirebaseAuth.getInstance();
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


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

        gmailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }




    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        Mauth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            Intent intent  = new Intent(getApplicationContext(), Home_Activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            FirebaseUser user = Mauth.getCurrentUser();
                            //   updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //   Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //   updateUI(null);
                        }

                        // ...
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
            finish();
        }

        super.onStart();
    }


}
