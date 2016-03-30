package com.example.staceefreeman.movieswithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ReviewMovie extends AppCompatActivity implements View.OnClickListener {

    TextView tvMovieTitle, tvYear, tvRated, tvRuntime;
    ImageView imMoviePoster;
    RatingBar ratingBar;
    EditText etComments;
    Button bCancel, bSubmit;

    ReviewDatabaseHelper reviewHelper = new ReviewDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_movie);

        tvMovieTitle = (TextView) findViewById(R.id.tvMovieTitle);
        tvYear = (TextView) findViewById(R.id.tvYear);
        tvRated = (TextView) findViewById(R.id.tvRated);
        tvRuntime = (TextView) findViewById(R.id.tvRuntime);
        imMoviePoster = (ImageView) findViewById(R.id.imMoviePoster);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        etComments = (EditText) findViewById(R.id.etComments);

        bCancel = (Button) findViewById(R.id.bCancel);
        bCancel.setOnClickListener(this);
        bSubmit = (Button) findViewById(R.id.bSubmit);
        bSubmit.setOnClickListener(this);

        String movieName = getIntent().getStringExtra("Movie");
        tvMovieTitle.setText(movieName);
        String rated = getIntent().getStringExtra("Rated");
        tvRated.setText("Rated: " + rated);

        String year = getIntent().getStringExtra("Year");
        tvYear.setText("(" + year + ")");


        String runtime = getIntent().getStringExtra("Runtime");
        tvRuntime.setText("Runtime: " + runtime);

        String posterStr = getIntent().getStringExtra("Poster");
        Picasso.with(ReviewMovie.this).load(posterStr).placeholder(R.drawable.placeholder).into(imMoviePoster);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bCancel:
                String name = getIntent().getStringExtra("Name");
                String email = getIntent().getStringExtra("Email");
                String username = getIntent().getStringExtra("Username");
                String password = getIntent().getStringExtra("Password");
                String major = getIntent().getStringExtra("Major");
                String interests = getIntent().getStringExtra("Interests");

                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("Name", name);
                i.putExtra("Email", email);
                i.putExtra("Username", username);
                i.putExtra("Password", password);
                i.putExtra("Major", major);
                i.putExtra("Interests", interests);

                startActivity(i);

                break;

            case R.id.bSubmit:
                String name2 = getIntent().getStringExtra("Name");
                String email2 = getIntent().getStringExtra("Email");
                String username2 = getIntent().getStringExtra("Username");
                String password2 = getIntent().getStringExtra("Password");
                String major2 = getIntent().getStringExtra("Major");
                String interests2 = getIntent().getStringExtra("Interests");
                String posterURL = getIntent().getStringExtra("Poster");
                String imdbID = getIntent().getStringExtra("imdbID");
                Float rating = ratingBar.getRating();
                String comments = etComments.getText().toString();
                String title = tvMovieTitle.getText().toString();

                Review review = new Review();
                review.setTitle(title);
                review.setImdbID(imdbID);
                review.setUsername(username2);
                review.setMajor(major2);
                review.setRating(rating);
                review.setComments(comments);
                review.setPoster(posterURL);

                reviewHelper.insertReview(review);
                Intent i2 = new Intent(ReviewMovie.this, MovieDetails.class);
                i2.putExtra("Name", name2);
                i2.putExtra("Email", email2);
                i2.putExtra("Username", username2);
                i2.putExtra("Password", password2);
                i2.putExtra("Major", major2);
                i2.putExtra("Interests", interests2);
                i2.putExtra("imdbID", imdbID);
                startActivity(i2);

        }
    }
}
