package com.example.staceefreeman.movieswithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    Button bEdit, bDashboard, bLogout;
    EditText etName, etEmail, etUsername, etMajor, etInterests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String name = getIntent().getStringExtra("Name");
        etName = (EditText) findViewById(R.id.etName);
        etName.setText(name);

        String email = getIntent().getStringExtra("Email");
        etEmail = (EditText) findViewById(R.id.etEmail);
        etEmail.setText(email);

        String username = getIntent().getStringExtra("Username");
        etUsername = (EditText) findViewById(R.id.etUsername);
        etUsername.setText(username);

        String major = getIntent().getStringExtra("Major");
        etMajor = (EditText) findViewById(R.id.etMajor);
        etMajor.setText(major);

        String interests = getIntent().getStringExtra("Interests");
        etInterests = (EditText) findViewById(R.id.etInterests);
        etInterests.setText(interests);

        bEdit = (Button) findViewById(R.id.bEdit);
        bEdit.setOnClickListener(this);
        bDashboard = (Button) findViewById(R.id.bDashboard);
        bDashboard.setOnClickListener(this);
        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bEdit:
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = getIntent().getStringExtra("Password");
                String major = etMajor.getText().toString();
                String interests = etInterests.getText().toString();

                Intent i = new Intent(Profile.this, EditProfile.class);
                i.putExtra("Name", name);
                i.putExtra("Email", email);
                i.putExtra("Username", username);
                i.putExtra("Password", password);
                i.putExtra("Major", major);
                i.putExtra("Interests", interests);
                startActivity(i);

                break;

            case R.id.bDashboard:
                String name2 = etName.getText().toString();
                String email2 = etEmail.getText().toString();
                String username2 = etUsername.getText().toString();
                String password2 = getIntent().getStringExtra("Password");
                String major2 = etMajor.getText().toString();
                String interests2 = etInterests.getText().toString();

                Intent i2 = new Intent(Profile.this, MainActivity.class);
                i2.putExtra("Name", name2);
                i2.putExtra("Email", email2);
                i2.putExtra("Username", username2);
                i2.putExtra("Password", password2);
                i2.putExtra("Major", major2);
                i2.putExtra("Interests", interests2);
                startActivity(i2);
                break;

            case R.id.bLogout:
                startActivity(new Intent(this, Login.class));
        }
    }
}
