package com.example.staceefreeman.movieswithfriends;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister, bCancel;
    EditText etName, etEmail, etUsername, etPassword, etConfirmPassword;

    UserDatabaseHelper userHelper = new UserDatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);
        bCancel = (Button) findViewById(R.id.bCancel);
        bCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bRegister:
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setUsername(username);
                user.setPassword(password);

                if (!password.equals(confirmPassword)) {
                    //popup msg
                    Toast alert = Toast.makeText(Register.this, "Passwords don't match!",
                            Toast.LENGTH_SHORT);
                    alert.show();
                } else if (!userHelper.isAvailable(username)) {
                    //popup msg
                    Toast alert = Toast.makeText(Register.this, "Username has already been taken.",
                            Toast.LENGTH_SHORT);
                    alert.show();
                } else {
                    //add user to database
                    userHelper.insertUser(user);
                    Intent i = new Intent(Register.this, CreateProfile.class);
                    i.putExtra("Name", name);
                    i.putExtra("Email", email);
                    i.putExtra("Username", username);
                    i.putExtra("Password", password);
                    startActivity(i);
                }
                break;

            case R.id.bCancel:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }
}