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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminDashboard extends AppCompatActivity implements View.OnClickListener {

    ListView scrollList;
    Button bLogout;
    ArrayList<HashMap<String, String>> res;
    UserDatabaseHelper helper = new UserDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        scrollList = (ListView) findViewById(R.id.scrollList);
        // returns an array list of hash maps
        // each hash map item holds a username and his/her status
        res = helper.adminSearch();

        // scroll list implements a Simple Adapter this time instead of our typical custom one
        // sets just the username and their status on to a CLICKABLE scroll list
        scrollList.setAdapter(new SimpleAdapter(this, res, R.layout.userlist_layout,
                new String[] {"username", "status"},
                new int[] {R.id.username, R.id.status}));

        scrollList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // on click - grabs that username and status
                HashMap<String, String> info = (HashMap<String, String>) parent.getItemAtPosition(position);
                Intent i = new Intent(AdminDashboard.this, AdminUserDetails.class);
                i.putExtra("Username", info.get("username"));
                i.putExtra("Status", info.get("status"));
                startActivity(i);
            }
        });


        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogout:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }

}
