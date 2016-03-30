package com.example.staceefreeman.movieswithfriends;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    CheckBox cbAdmin;
    Button bLogin, bCancel;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;

    UserDatabaseHelper userHelper = new UserDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        cbAdmin = (CheckBox) findViewById(R.id.cbAdmin);
        bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(this);
        bCancel = (Button) findViewById(R.id.bCancel);
        bCancel.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bLogin:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // checks to see if admin button is selected
                // cannot log in as admin if the check box is left unchecked
                if (cbAdmin.isChecked()) {
                    // built in admin accounts
                    // admin is constant username
                    // passwords are any of our last names
                    if (username.equals("admin") && (password.equals("freeman")
                            || password.equals("murrell")
                            || password.equals("brown")
                            || password.equals("marland"))) {
                        Intent i = new Intent(Login.this, AdminDashboard.class);
                        startActivity(i);
                    } else {
                        Toast alert = Toast.makeText(Login.this, "Admin username/password is incorrect!", Toast.LENGTH_SHORT);
                        alert.show();
                    }
                } else {
                    String dbName = userHelper.searchName(username);
                    String dbEmail = userHelper.searchEmail(username);
                    String dbPass = userHelper.searchPassword(username);
                    String dbMajor = userHelper.searchMajor(username);
                    String dbInterests = userHelper.searchInterests(username);
                    String dbStatus = userHelper.searchStatus(username);

                    // first checks that password matches
                    if (dbPass.equals(password)) {
                        // then checks status of the account
                        // banned account alert
                        if (dbStatus.equals("Banned")) {
                            Toast alert = Toast.makeText(Login.this, "Your account is banned. Please contact an admin.", Toast.LENGTH_SHORT);
                            alert.show();
                        } else if (dbStatus.equals("Locked")) { //locked account alert
                            Toast alert2 = Toast.makeText(Login.this, "Your account is locked. Please contact an admin.", Toast.LENGTH_SHORT);
                            alert2.show();
                        } else { // else, account is "Active" so login continues successfully
                            Intent i = new Intent(Login.this, MainActivity.class);
                            i.putExtra("Name", dbName);
                            i.putExtra("Email", dbEmail);
                            i.putExtra("Username", username);
                            i.putExtra("Password", dbPass);
                            i.putExtra("Major", dbMajor);
                            i.putExtra("Interests", dbInterests);
                            startActivity(i);
                        }
                    } else {
                        Toast alert = Toast.makeText(Login.this, "Username/password is incorrect!", Toast.LENGTH_SHORT);
                        alert.show();
                    }
                }
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(Login.this, Register.class));
                break;

            case R.id.bCancel:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }

}

