package com.example.staceefreeman.movieswithfriends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    // View lookup cache
    private static class ViewHolder {
        TextView title, year;
        ImageView poster;
    }

    /**
     * Constructor for the custom array adapter
     *
     * @param context
     * @param movies an array of Movie objects to be put onto the list view
     */
    public MovieArrayAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.movielist_layout, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.movielist_layout, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.year = (TextView) convertView.findViewById(R.id.year);
            viewHolder.poster = (ImageView) convertView.findViewById(R.id.poster);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.title.setText(movie.title);
        viewHolder.year.setText("(" + movie.year + ")");
        Picasso.with(getContext()).load(movie.poster).placeholder(R.drawable.placeholder).into(viewHolder.poster);

        // Return the completed view to render on screen
        return convertView;
    }
}

