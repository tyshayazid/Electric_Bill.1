package com.example.electricbill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.electricbill.R;

public class login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
                    Toast.makeText(login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, homepage.class);
                    startActivity(intent);
                    finish(); // Close the login activity


                } else {
                    Toast.makeText(login.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}