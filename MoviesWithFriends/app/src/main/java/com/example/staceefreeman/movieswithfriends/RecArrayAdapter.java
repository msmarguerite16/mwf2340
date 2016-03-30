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

public class RecArrayAdapter extends ArrayAdapter<Review> {

    // View lookup cache
    private static class ViewHolder {
        TextView title, avgRating;
        ImageView poster;
    }

    /**
     * Constructor for the custom array adapter
     *
     * @param context
     * @param filteredMovies an array of Review objects to be put onto the list view
     */
    public RecArrayAdapter(Context context, ArrayList<Review> filteredMovies) {
        super(context, R.layout.movielist_layout, filteredMovies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Review review = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.movielist_layout, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.avgRating = (TextView) convertView.findViewById(R.id.year);
            viewHolder.poster = (ImageView) convertView.findViewById(R.id.poster);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.title.setText(review.title);
        viewHolder.avgRating.setText(review.rating + " Stars");
        Picasso.with(getContext()).load(review.poster).placeholder(R.drawable.placeholder).into(viewHolder.poster);

        // Return the completed view to render on screen
        return convertView;
    }
}

