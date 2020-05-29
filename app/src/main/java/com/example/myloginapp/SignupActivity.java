package com.example.myloginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener
{

    private EditText signupName,signupEmail,signupPassword;
    private Button signup,loginLink;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupName=(EditText)findViewById(R.id.editTextName);
        signupEmail=(EditText)findViewById(R.id.editTextSignUpEmail);
        signupPassword=(EditText)findViewById(R.id.editTextSignUpPassword);
        signup=(Button)findViewById(R.id.buttonSignUp);
        loginLink=(Button)findViewById(R.id.buttonLoginLink);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        mAuth=FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);

        signup.setOnClickListener(this);
        loginLink.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonLoginLink){
            Intent intent=new Intent(SignupActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else{
            progressBar.setVisibility(View.VISIBLE);
            userReg();
        }
    }

    private void userReg() {
        String email = signupEmail.getText().toString().trim();
        String password = signupPassword.getText().toString().trim();
        if (email.isEmpty()) {
            signupEmail.setError("Enter a Email!");
            signupEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signupEmail.setError("Enter a valid Email address!");
            signupEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            signupPassword.setError("Enter a Password!");
            signupPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            signupPassword.setError("Minimum length of the password should be 6!");
            signupPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful())
                    Toast.makeText(getApplicationContext(), "Register is Successful!", Toast.LENGTH_SHORT).show();
                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"User is Already Registered!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Error : "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
