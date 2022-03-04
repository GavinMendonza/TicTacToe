package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    EditText logEmail, logPass;
    Button login, signup;
    ProgressBar progressBar;
    FirebaseAuth _auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logEmail = findViewById(R.id.logEmail);
        logPass = findViewById(R.id.logPass);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.goSignup_btn);
        progressBar = findViewById(R.id.progressBar);
        _auth = FirebaseAuth.getInstance();

        login.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            String email = logEmail.getText().toString().trim();
            String password = logPass.getText().toString().trim();

            if (email.isEmpty()) {
                logEmail.setError("Email is required");
                logEmail.requestFocus();
            } else if (password.isEmpty()) {
                logPass.setError("Password is required");
                logPass.requestFocus();
            } else {
                _auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainMenu.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        signup.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = _auth.getCurrentUser();
        if(user != null){
            Intent i = new Intent(LoginActivity.this, MainMenu.class);
            startActivity(i);
            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
// empty so nothing happens
    }
}