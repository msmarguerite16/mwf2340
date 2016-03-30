package com.example.staceefreeman.movieswithfriends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewArrayAdapter extends ArrayAdapter<Review> {
    // View lookup cache
    private static class ViewHolder {
        TextView comments, uname, rating, major;
    }

    /**
     * Constructor for the custom array adapter
     *
     * @param context
     * @param reviews an array of Review objects to be put onto the list view
     */
    public ReviewArrayAdapter(Context context, ArrayList<Review> reviews) {
        super(context, R.layout.reviewlist_layout, reviews);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Review r = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.reviewlist_layout, parent, false);
            viewHolder.comments = (TextView) convertView.findViewById(R.id.comments);
            viewHolder.uname = (TextView) convertView.findViewById(R.id.user);
            viewHolder.major = (TextView) convertView.findViewById(R.id.major);
            viewHolder.rating = (TextView) convertView.findViewById(R.id.rating);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.comments.setText('"' + r.getComments() + '"');
        viewHolder.uname.setText("- " + r.getUsername());
        viewHolder.major.setText(r.getMajor());
        viewHolder.rating.setText(r.getRating().toString() + " out of 5.0 Stars.");

        // Return the completed view to render on screen
        return convertView;
    }
}
