package com.example.staceefreeman.movieswithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Search extends AppCompatActivity {

    TextView searchTxt2;
    ListView scrollList;

    String urlStr;
    RequestQueue requestQueue;
    ArrayList<Movie> movieArrList;
    MovieArrayAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String searchVal = getIntent().getStringExtra("Search Value");
        searchTxt2 = (TextView) findViewById(R.id.searchTxt2);
        searchTxt2.setText(searchVal);

        scrollList = (ListView) findViewById(R.id.scrollList);

        urlStr = "http://www.omdbapi.com/?s=" + searchVal.replace(" ", "%20");

        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, urlStr, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // ArrayList of Movie objects for the scroll list
                            movieArrList = new ArrayList<Movie>();

                            // Getting JSON Array of all the movies search results
                            JSONArray movies = response.getJSONArray("Search");

                            // looping through All Movies and getting singular movie objects
                            for (int i = 0; i < movies.length(); i++) {
                                JSONObject m = movies.getJSONObject(i);

                                // current movie object to be filled and added to over array list of movies
                                Movie movie = new Movie();

                                // adding all attributes to the current movie
                                movie.setTitle(m.getString("Title"));
                                movie.setYear(m.getString("Year"));
                                movie.setImdbID(m.getString("imdbID"));
                                movie.setPoster(m.getString("Poster"));

                                // adding student to students list
                                movieArrList.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        customAdapter = new MovieArrayAdapter(Search.this, movieArrList);
                        scrollList.setAdapter(customAdapter);

                        scrollList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String name = getIntent().getStringExtra("Name");
                                String email = getIntent().getStringExtra("Email");
                                String username = getIntent().getStringExtra("Username");
                                String password = getIntent().getStringExtra("Password");
                                String major = getIntent().getStringExtra("Major");
                                String interests = getIntent().getStringExtra("Interests");

                                Movie movie = (Movie) parent.getItemAtPosition(position);
                                Intent i = new Intent(Search.this, MovieDetails.class);
                                i.putExtra("Name", name);
                                i.putExtra("Email", email);
                                i.putExtra("Username", username);
                                i.putExtra("Password", password);
                                i.putExtra("Major", major);
                                i.putExtra("Interests", interests);

                                i.putExtra("imdbID", movie.getImdbID());
                                startActivity(i);
                            }
                        });

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
}
