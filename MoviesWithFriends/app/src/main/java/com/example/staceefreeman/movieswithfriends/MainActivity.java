package com.example.staceefreeman.movieswithfriends;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bSearch, bRecommend, bProfile, bLogout;
    TextView welcomeTxt1;
    EditText searchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTxt1 = (TextView) findViewById(R.id.welcomeTxt1);
        welcomeTxt1.setText("Hey " + getIntent().getStringExtra("Name") + "!");

        searchInput = (EditText) findViewById(R.id.searchInput);

        bSearch = (Button) findViewById(R.id.bSearch);
        bSearch.setOnClickListener(this);

        bRecommend = (Button) findViewById(R.id.bRecommend);
        bRecommend.setOnClickListener(this);
        bProfile = (Button) findViewById(R.id.bProfile);
        bProfile.setOnClickListener(this);
        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bProfile:
                String name = getIntent().getStringExtra("Name");
                String email = getIntent().getStringExtra("Email");
                String username = getIntent().getStringExtra("Username");
                String password = getIntent().getStringExtra("Password");
                String major = getIntent().getStringExtra("Major");
                String interests = getIntent().getStringExtra("Interests");

                Intent i = new Intent(MainActivity.this, Profile.class);
                i.putExtra("Name", name);
                i.putExtra("Email", email);
                i.putExtra("Username", username);
                i.putExtra("Password", password);
                i.putExtra("Major", major);
                i.putExtra("Interests", interests);
                startActivity(i);
                break;

            case R.id.bSearch:
                String name2 = getIntent().getStringExtra("Name");
                String email2 = getIntent().getStringExtra("Email");
                String username2 = getIntent().getStringExtra("Username");
                String password2 = getIntent().getStringExtra("Password");
                String major2 = getIntent().getStringExtra("Major");
                String interests2 = getIntent().getStringExtra("Interests");
                String searchVal = searchInput.getText().toString();

                Intent i2 = new Intent(this, Search.class);
                i2.putExtra("Name", name2);
                i2.putExtra("Email", email2);
                i2.putExtra("Username", username2);
                i2.putExtra("Password", password2);
                i2.putExtra("Major", major2);
                i2.putExtra("Interests", interests2);
                i2.putExtra("Search Value", searchVal);
                startActivity(i2);
                break;

            case R.id.bRecommend:
                String name3 = getIntent().getStringExtra("Name");
                String email3 = getIntent().getStringExtra("Email");
                String username3 = getIntent().getStringExtra("Username");
                String password3 = getIntent().getStringExtra("Password");
                String major3 = getIntent().getStringExtra("Major");
                String interests3 = getIntent().getStringExtra("Interests");

                Intent i3 = new Intent(this, RecSearch.class);
                i3.putExtra("Name", name3);
                i3.putExtra("Email", email3);
                i3.putExtra("Username", username3);
                i3.putExtra("Password", password3);
                i3.putExtra("Major", major3);
                i3.putExtra("Interests", interests3);
                startActivity(i3);
                break;

            case R.id.bLogout:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }
}
