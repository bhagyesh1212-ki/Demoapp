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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.one.demo.R;
import com.one.demo.model.UserModel;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button signup_button;
    private EditText semail, sname, spassword, sc_password;
    private TextView login_here;
    private  ImageView toggleConfirmPassword,togglePassword,toggleloginpassword;
    FirebaseDatabase firebaseDatabase;
    private  ProgressDialog progressDialog;
    DatabaseReference myRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        signup_button = findViewById(R.id.signup_button);
        semail = findViewById(R.id.email);
        sname = findViewById(R.id.name);
        spassword = findViewById(R.id.password);
        sc_password = findViewById(R.id.c_password);
        login_here = findViewById(R.id.login_here);
        togglePassword = findViewById(R.id.togglePassword);
        toggleConfirmPassword = findViewById(R.id.toggleConfirmPassword);
        firebaseDatabase = FirebaseDatabase.getInstance();

        login_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (semail.getText().toString().isBlank() || spassword.getText().toString().isBlank() || sname.getText().toString().isBlank() || spassword.getText().toString().isBlank() || sc_password.getText().toString().isBlank()) {
                    Toast.makeText(RegisterActivity.this, "Please fill all detail...", Toast.LENGTH_SHORT).show();
                } else if (sname.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Name required", Toast.LENGTH_SHORT).show();
                } else if (semail.getText().toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(semail.getText().toString()).matches()) {
                    Toast.makeText(RegisterActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                } else if (sc_password.getText().toString().isEmpty()||spassword.getText().toString().isEmpty()||!spassword.getText().toString().equals(sc_password.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Password and confirm password not match", Toast.LENGTH_LONG).show();
                } else {
                    registerNewUser();
                }
            }
        });
        togglePassword.setOnClickListener(view -> togglePasswordVisibility(spassword, togglePassword));
        toggleConfirmPassword.setOnClickListener(view -> togglePasswordVisibility(sc_password, toggleConfirmPassword));
    }


    private void registerNewUser() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String email, password;
        email = semail.getText().toString();
        password = spassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.setCancelable(false);

                            UserModel model = new UserModel(sname.getText().toString(), semail.getText().toString(), spassword.getText().toString());
                            String id = task.getResult().getUser().getUid();
                            firebaseDatabase.getReference().child("UserDetail").child(id).setValue(model);
                            Intent i = new Intent(RegisterActivity.this, SplashActivity.class);
                            startActivity(i);
                            finish();
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            progressDialog.setCancelable(true);
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(RegisterActivity.this, SplashActivity.class);
            startActivity(i);
            finish();
        }
    }
    private void togglePasswordVisibility(EditText passwordField, ImageView toggleIcon) {
        if (passwordField.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
            toggleIcon.setImageResource(R.drawable.eye); // Visible icon
            passwordField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            toggleIcon.setImageResource(R.drawable.invisible); // Hidden icon
            passwordField.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        passwordField.setSelection(passwordField.getText().length()); // Keep cursor at end
    }

}




