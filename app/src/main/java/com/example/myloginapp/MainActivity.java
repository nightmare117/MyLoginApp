package com.example.myloginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText loginEmail,loginPassword;
    private Button loginButton,loginLink;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginEmail=(EditText) findViewById(R.id.editTextLoginEmail);
        loginPassword=(EditText) findViewById(R.id.editTextLoginPassword);
        loginButton=(Button)findViewById(R.id.buttonLogin);
        loginLink=(Button)findViewById(R.id.buttonSignUpLink);
        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(this);
        loginLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonLogin){
            userLogin();
        }
        else{
            Intent intent =new Intent(MainActivity.this,SignupActivity.class);
            startActivity(intent);
        }
    }

    private void userLogin() {
        String email=loginEmail.getText().toString().trim();
        String password=loginPassword.getText().toString().trim();
        if (email.isEmpty()) {
            loginEmail.setError("Enter a Email!");
            loginEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginEmail.setError("Enter a valid Email address!");
            loginEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            loginPassword.setError("Enter a Password!");
            loginPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            loginPassword.setError("Minimum length of the password should be 6!");
            loginPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(),afterLoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Login Unsuccessful!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
