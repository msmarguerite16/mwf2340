package com.example.staceefreeman.movieswithfriends;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RecSearch extends AppCompatActivity implements View.OnClickListener {

    Button bRSearch, bDash, bLog;
    Spinner ratingFilt, majorFilt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_search);

        bRSearch = (Button) findViewById(R.id.bRSearch);
        bRSearch.setOnClickListener(this);

        bDash = (Button) findViewById(R.id.bDash);
        bDash.setOnClickListener(this);

        bLog = (Button) findViewById(R.id.bLog);
        bLog.setOnClickListener(this);

        ratingFilt = (Spinner) findViewById(R.id.ratingFilt);
        majorFilt = (Spinner) findViewById(R.id.majorFilt);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRSearch:
                String name = getIntent().getStringExtra("Name");
                String email = getIntent().getStringExtra("Email");
                String username = getIntent().getStringExtra("Username");
                String password = getIntent().getStringExtra("Password");
                String major = getIntent().getStringExtra("Major");
                String interests = getIntent().getStringExtra("Interests");
                String mFilt = String.valueOf(majorFilt.getSelectedItem());
                String rFilt = String.valueOf(ratingFilt.getSelectedItem());

                Intent i = new Intent(this, RecResults.class);
                i.putExtra("Name", name);
                i.putExtra("Email", email);
                i.putExtra("Username", username);
                i.putExtra("Password", password);
                i.putExtra("Major", major);
                i.putExtra("Interests", interests);
                i.putExtra("Major Filter", mFilt);
                i.putExtra("Rating Filter", rFilt);

                startActivity(i);
                break;

            case R.id.bDash:
                String name2 = getIntent().getStringExtra("Name");
                String email2 = getIntent().getStringExtra("Email");
                String username2 = getIntent().getStringExtra("Username");
                String password2 = getIntent().getStringExtra("Password");
                String major2 = getIntent().getStringExtra("Major");
                String interests2 = getIntent().getStringExtra("Interests");

                Intent i2 = new Intent(RecSearch.this, MainActivity.class);
                i2.putExtra("Name", name2);
                i2.putExtra("Email", email2);
                i2.putExtra("Username", username2);
                i2.putExtra("Password", password2);
                i2.putExtra("Major", major2);
                i2.putExtra("Interests", interests2);
                startActivity(i2);
                break;

            case R.id.bLog:
                startActivity(new Intent(this, Login.class));
        }
    }
}
