package com.example.staceefreeman.movieswithfriends;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieDetails extends AppCompatActivity implements View.OnClickListener {

    TextView tvMovieTitle, tvYear, tvRated, tvRuntime;
    TextView tvDirector, tvActors, tvPlot;
    ImageView imMoviePoster;
    Button bRateMovie, bDashboard, bLogout;
    Movie movie;
    RatingBar ratingBar;
    float numStars;

    String urlStr, rated, year, poster, runtime;
    RequestQueue requestQueue;

    ArrayList<Review> reviewArrList;
    Review nullFiller;
    ReviewArrayAdapter customAdapter;
    ListView reviews;

    ReviewDatabaseHelper helper = new ReviewDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        final String imdbID = getIntent().getStringExtra("imdbID");
        urlStr = "http://www.omdbapi.com/?i=" + imdbID;

        tvMovieTitle = (TextView) findViewById(R.id.tvMovieTitle);
        tvRated = (TextView) findViewById(R.id.tvRated);
        tvYear = (TextView) findViewById(R.id.tvYear);
        tvRuntime = (TextView) findViewById(R.id.tvRuntime);
        imMoviePoster = (ImageView) findViewById(R.id.imMoviePoster);
        tvDirector = (TextView) findViewById(R.id.tvDirector);
        tvActors = (TextView) findViewById(R.id.tvActors);
        tvPlot = (TextView) findViewById(R.id.tvPlot);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        ratingBar.setFocusable(false);

        reviewArrList = helper.searchReviews(imdbID);

        if (reviewArrList.size() > 0) {
            customAdapter = new ReviewArrayAdapter(MovieDetails.this, reviewArrList);
            customAdapter.notifyDataSetChanged();
            reviews = (ListView) findViewById(R.id.reviews);
            reviews.setAdapter(customAdapter);
        }

        bRateMovie = (Button) findViewById(R.id.bRateMovie);
        bDashboard = (Button) findViewById(R.id.bDashboard);
        bDashboard.setOnClickListener(this);
        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);

        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, urlStr, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // movie object to be filled
                            movie = new Movie();

                            // adding all attributes to the current movie
                            movie.setTitle(response.getString("Title"));
                            poster = response.getString("Poster");
                            movie.setPoster(poster);
                            movie.setDirector(response.getString("Director"));
                            movie.setActors(response.getString("Actors"));
                            movie.setPlot(response.getString("Plot"));

                            rated = response.getString("Rated");
                            movie.setRated(rated);
                            year = response.getString("Year");
                            movie.setYear(year);
                            runtime = response.getString("Runtime");
                            movie.setRuntime(runtime);
                            tvMovieTitle.setText(movie.getTitle());
                            tvYear.setText("(" + movie.getYear() + ")");
                            tvRated.setText("Rated: " + movie.getRated());
                            tvRuntime.setText("Runtime: " + movie.getRuntime());
                            Picasso.with(MovieDetails.this).load(movie.getPoster()).placeholder(R.drawable.placeholder).into(imMoviePoster);
                            tvDirector.setText(movie.getDirector());
                            tvActors.setText(movie.getActors());
                            tvPlot.setText(movie.getPlot());

                            numStars = Float.parseFloat(response.getString("imdbRating"));
                            numStars = numStars/2.0f;

                            ratingBar.setRating(numStars);



                            bRateMovie.setOnClickListener(new Button.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String name = getIntent().getStringExtra("Name");
                                    String email = getIntent().getStringExtra("Email");
                                    String username = getIntent().getStringExtra("Username");
                                    String password = getIntent().getStringExtra("Password");
                                    String major = getIntent().getStringExtra("Major");
                                    String interests = getIntent().getStringExtra("Interests");

                                    String imdbID = getIntent().getStringExtra("imdbID");

                                    Intent i = new Intent(MovieDetails.this, ReviewMovie.class);
                                    i.putExtra("Name", name);
                                    i.putExtra("Email", email);
                                    i.putExtra("Username", username);
                                    i.putExtra("Password", password);
                                    i.putExtra("Major", major);
                                    i.putExtra("Interests", interests);
                                    i.putExtra("imdbID", imdbID);
                                    i.putExtra("Movie", movie.getTitle());
                                    i.putExtra("Year", year);
                                    i.putExtra("Rated", rated);
                                    i.putExtra("Runtime", runtime);
                                    i.putExtra("Poster", poster);


//                                    i.putExtra("defaultRating", numStars);


                                    startActivity(i);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                });
        requestQueue.add(jor);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bDashboard:
                String name = getIntent().getStringExtra("Name");
                String email = getIntent().getStringExtra("Email");
                String username = getIntent().getStringExtra("Username");
                String password = getIntent().getStringExtra("Password");
                String major = getIntent().getStringExtra("Major");
                String interests = getIntent().getStringExtra("Interests");

                Intent i = new Intent(MovieDetails.this, MainActivity.class);
                i.putExtra("Name", name);
                i.putExtra("Email", email);
                i.putExtra("Username", username);
                i.putExtra("Password", password);
                i.putExtra("Major", major);
                i.putExtra("Interests", interests);

                startActivity(i);

                break;

            case R.id.bLogout:
                startActivity(new Intent(this, Login.class));
        }
    }

}
