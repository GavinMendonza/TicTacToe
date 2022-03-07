package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tictactoe.Models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth _auth;

    EditText signUname, signEmail, signPass, signRepass;
    Button signup, login;
    FirebaseDatabase database;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUname = findViewById(R.id.signUname);
        signPass = findViewById(R.id.signPass);
        signRepass = findViewById(R.id.signRepass);
        signEmail = findViewById(R.id.signEmail);
        login = findViewById(R.id.goLogin_btn);
        signup = findViewById(R.id.signup);
        progressBar = findViewById(R.id.progressBar);
        _auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://auth1-14f60-default-rtdb.asia-southeast1.firebasedatabase.app/");
//        database = FirebaseDatabase.getInstance();  //this doesnt work because we created rtdb starting fb or somethn


        signup.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            String username = signUname.getText().toString().trim();
            String email = signEmail.getText().toString().trim();
            String password = signPass.getText().toString().trim();
            String repass = signRepass.getText().toString().trim();

            if(!repass.equals(password)){
                Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
            else if(username.isEmpty()) {
                signUname.setError("Username is required");
                signUname.requestFocus();
            }
            else if(email.isEmpty()){
                signEmail.setError("Email is required");
                signEmail.requestFocus();
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                signEmail.setError("Provide valid Email");
                signEmail.requestFocus();
            }
            else if(password.isEmpty()){
                signPass.setError("Password is required");
                signPass.requestFocus();
            }
            else if(password.length() < 6){
                signPass.setError("Password should be minimum of 6 characters");
                signPass.requestFocus();
            }
            else if(repass.isEmpty()){
                signRepass.setError("Retype password");
                signRepass.requestFocus();
            }
            else {
                _auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        //start of saving user data
                        int score=0;
                        Users user=new Users(username,email,password,score);
                        String id=task.getResult().getUser().getUid();
                        database.getReference().child("Users").child(id).setValue(user);

                        //end of saving user data

                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(RegisterActivity.this, "User registered", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegisterActivity.this, MainMenu.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                    progressBar.setVisibility(View.GONE);
                        Toast.makeText(RegisterActivity.this, "Failed to registered", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        login.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

        });
    }
}