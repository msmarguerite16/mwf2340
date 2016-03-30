package com.example.staceefreeman.movieswithfriends;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecResults extends AppCompatActivity {

    TextView mFilt;
    ListView scrollList2;

    ArrayList<Review> filtList;
    RecArrayAdapter customAdapter;

    ReviewDatabaseHelper helper = new ReviewDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_results);

        String major = getIntent().getStringExtra("Major Filter");
        String rating = getIntent().getStringExtra("Rating Filter");

        mFilt = (TextView) findViewById(R.id.mFilt);
        mFilt.setText(major);

        scrollList2 = (ListView) findViewById(R.id.scrollList2);

        filtList = helper.getMajorRec(major, rating);

        customAdapter = new RecArrayAdapter(RecResults.this, filtList);
        scrollList2.setAdapter(customAdapter);

        scrollList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = getIntent().getStringExtra("Name");
                String email = getIntent().getStringExtra("Email");
                String username = getIntent().getStringExtra("Username");
                String password = getIntent().getStringExtra("Password");
                String major = getIntent().getStringExtra("Major");
                String interests = getIntent().getStringExtra("Interests");

                Review review = (Review) parent.getItemAtPosition(position);
                Intent i = new Intent(RecResults.this, MovieDetails.class);
                i.putExtra("Name", name);
                i.putExtra("Email", email);
                i.putExtra("Username", username);
                i.putExtra("Password", password);
                i.putExtra("Major", major);
                i.putExtra("Interests", interests);

                i.putExtra("imdbID", review.getImdbID());
                startActivity(i);
            }
        });

    }
}
