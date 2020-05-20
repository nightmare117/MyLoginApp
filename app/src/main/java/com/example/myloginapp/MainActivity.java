package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText loginEmail,loginPassword;
    private Button loginButton,loginLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginEmail=(EditText) findViewById(R.id.editTextLoginEmail);
        loginPassword=(EditText) findViewById(R.id.editTextLoginPassword);
        loginButton=(Button)findViewById(R.id.buttonLogin);
        loginLink=(Button)findViewById(R.id.buttonSignUpLink);

        loginButton.setOnClickListener(this);
        loginLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonLogin){

        }
        else{
            Intent intent =new Intent(MainActivity.this,SignupActivity.class);
            startActivity(intent);
        }
    }
}
