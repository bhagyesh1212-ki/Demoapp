package com.one.demo.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
    FirebaseDatabase firebaseDatabase;
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
        firebaseDatabase = FirebaseDatabase.getInstance();

        login_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

    private void registerNewUser() {
        String email, password;
        email = semail.getText().toString();
        password = spassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            UserModel model = new UserModel(sname.getText().toString(), semail.getText().toString(), spassword.getText().toString());
                            String id = task.getResult().getUser().getUid();
                            firebaseDatabase.getReference().child("UserDetail").child(id).setValue(model);
                            Intent i = new Intent(RegisterActivity.this, SplashActivity.class);
                            startActivity(i);
                            finish();
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
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
}




