package com.example.staceefreeman.movieswithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminUserDetails extends AppCompatActivity implements View.OnClickListener {

    TextView username;
    Spinner statusSpin;
    ListView reviews;
    Button bSave, bDashboard, bLogout;

    ArrayList<Review> userReviews;
    // have a review db helper to get each user's reviews
    ReviewDatabaseHelper rHelper = new ReviewDatabaseHelper(this);

    // have a user db helper to get and update each user's status info
    UserDatabaseHelper uHelper = new UserDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_details);

        username = (TextView) findViewById(R.id.usernameTxt);
        username.setText(getIntent().getStringExtra("Username"));

        String status = getIntent().getStringExtra("Status");
        statusSpin = (Spinner) findViewById(R.id.sStatus);
        ArrayAdapter myAdap = (ArrayAdapter) statusSpin.getAdapter();
        int spinnerPosition = myAdap.getPosition(status);
        statusSpin.setSelection(spinnerPosition);
        // sets the initial status of the user to the one pulled from the db
        // however, the admin can click and get a drop down of choices
        // if they change it and press save, it updates the db

        reviews = (ListView) findViewById(R.id.reviews);
        // getting every review that user has made
        userReviews = rHelper.getUsersReviews(getIntent().getStringExtra("Username"));
        reviews.setAdapter(new ReviewArrayAdapter(AdminUserDetails.this, userReviews));

        bSave = (Button) findViewById(R.id.bSave);
        bSave.setOnClickListener(this);
        bDashboard = (Button) findViewById(R.id.bDashboard);
        bDashboard.setOnClickListener(this);
        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bSave:
                String username = getIntent().getStringExtra("Username");
                String status = String.valueOf(statusSpin.getSelectedItem());

                uHelper.editUserStatus(username, status);
                // if the admin presses "Save" it updates the db with the user's new status
                // and goes back to the admin dashboard

                Intent i = new Intent(AdminUserDetails.this, AdminDashboard.class);
                //i.putExtra("Username", username);
                //i.putExtra("Status", status);
                startActivity(i);


                break;

            case R.id.bDashboard:
                startActivity(new Intent(this, AdminDashboard.class));
                break;

            case R.id.bLogout:
                startActivity(new Intent(this, Login.class));
        }
    }
}
