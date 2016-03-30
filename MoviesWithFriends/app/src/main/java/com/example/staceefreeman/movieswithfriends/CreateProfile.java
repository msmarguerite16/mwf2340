package com.example.staceefreeman.movieswithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CreateProfile extends AppCompatActivity implements View.OnClickListener {

    Button bSave;
    EditText etName, etEmail, etUsername, etInterests;
    Spinner sMajor;

    UserDatabaseHelper helper = new UserDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        String name = getIntent().getStringExtra("Name");
        etName = (EditText) findViewById(R.id.etName);
        etName.setText(name);

        String email = getIntent().getStringExtra("Email");
        etEmail = (EditText) findViewById(R.id.etEmail);
        etEmail.setText(email);

        String username = getIntent().getStringExtra("Username");
        etUsername = (EditText) findViewById(R.id.etUsername);
        etUsername.setText(username);

        sMajor = (Spinner) findViewById(R.id.sMajor);
        etInterests = (EditText) findViewById(R.id.etInterests);
        bSave = (Button) findViewById(R.id.bSave);
        bSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bSave:
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = getIntent().getStringExtra("Password");
                String major = String.valueOf(sMajor.getSelectedItem());
                String interests = etInterests.getText().toString();

                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setUsername(username);
                user.setPassword(password);
                user.setMajor(major);
                user.setInterests(interests);

                helper.editUserProfile(user);

                Intent i = new Intent(CreateProfile.this, Profile.class);
                i.putExtra("Name", name);
                i.putExtra("Email", email);
                i.putExtra("Username", username);
                i.putExtra("Password", password);
                i.putExtra("Major", major);
                i.putExtra("Interests", interests);
                startActivity(i);

                break;
        }
    }
}
