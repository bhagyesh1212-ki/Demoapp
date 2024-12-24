package com.one.demo.ui.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.one.demo.R;
import com.one.demo.model.UserModel;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button login_button;
    private TextView google_button;
    private EditText semail, spassword;
    private TextView register_here;
    private DatabaseReference databaseRef;
    private ImageView toggle_login_password;
    private ProgressBar progressdialogue;
    private static final int RC_SIGN_IN = 55;  // Can be any integer unique to the Activity.

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();
        login_button = findViewById(R.id.login_button);
        semail = findViewById(R.id.email);
        spassword = findViewById(R.id.password);
        google_button = findViewById(R.id.google_button);
        register_here = findViewById(R.id.register_here);
        progressdialogue = findViewById(R.id.progressdialogue);
        mAuth = FirebaseAuth.getInstance();
        toggle_login_password = findViewById(R.id.toggle_login_password);

        databaseRef = FirebaseDatabase.getInstance().getReference("UserDetail");
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);

        google_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the sign-in intent
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        register_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);

            }
        });
        toggle_login_password.setOnClickListener(view -> togglePasswordVisibility(spassword, toggle_login_password));

        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = semail.getText().toString();
                String password = spassword.getText().toString();
                if (email.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email must required", Toast.LENGTH_SHORT).show();
                } else if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                    Toast.makeText(LoginActivity.this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "password must required", Toast.LENGTH_SHORT).show();
                } else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
                    Toast.makeText(LoginActivity.this, "Password must contain at least 8 characters, including uppercase, lowercase, a number, and a special character", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser();
                }
            }
        });
    }

    private void togglePasswordVisibility(EditText passwordField, ImageView toggleIcon) {
        if (passwordField.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
            toggleIcon.setImageResource(R.drawable.visible); // Visible icon
            passwordField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            toggleIcon.setImageResource(R.drawable.invisiblee); // Hidden icon
            passwordField.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        passwordField.setSelection(passwordField.getText().length());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        showProgressBar(true);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google sign-in was successful
                GoogleSignInAccount account = task.getResult(ApiException.class);
                // Get the user's ID token and authenticate with Firebase
                String idToken = account.getIdToken();
                AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                showProgressBar(false);
                                if (task.isSuccessful()) {
                                    // Sign-in success, update UI with the signed-in user's information
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    UserModel userModel = new UserModel(user.getDisplayName(), user.getEmail(), null);
                                    databaseRef.child(user.getUid()).setValue(userModel);
                                    String displayName = user.getDisplayName();
                                    Toast.makeText(LoginActivity.this, "Welcome " + displayName, Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(LoginActivity.this, BottomNavigation.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    // Sign-in failed, display a message to the user
                                    Toast.makeText(LoginActivity.this, "Sign-in failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } catch (ApiException e) {
                // Google sign-in failed
                Toast.makeText(LoginActivity.this, "Google Sign-in Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void showProgressBar(boolean show) {
        if (show) {
            progressdialogue.setVisibility(View.VISIBLE);
        } else {
            progressdialogue.setVisibility(View.GONE);
        }
    }

    private void loginUser() {
        showProgressBar(true);
        String email, password;
        email = semail.getText().toString();
        password = spassword.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        showProgressBar(false);
                        if (task.isSuccessful()) {
                            Intent i = new Intent(LoginActivity.this, BottomNavigation.class);
                            startActivity(i);
                            finish();
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            showProgressBar(false);
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "User doesn't exist Register Now",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(LoginActivity.this, SplashActivity.class);
            startActivity(i);
            finish();
        }
    }
}