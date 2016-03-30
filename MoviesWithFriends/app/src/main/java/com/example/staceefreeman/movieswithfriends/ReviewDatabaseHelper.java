package com.example.staceefreeman.movieswithfriends;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class ReviewDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "reviews.db";
    private static final String TABLE_NAME = "reviews";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMDBID = "imdbID";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_POSTER = "poster";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_MAJOR = "major";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_COMMENTS = "comments";

    SQLiteDatabase db;
    private static final String TABLE_CREATE = "CREATE TABLE reviews (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "imdbID TEXT NOT NULL, title TEXT NOT NULL, poster TEXT NOT NULL, username TEXT NOT NULL, major TEXT NOT NULL,"
            + "rating FLOAT NOT NULL, comments TEXT NOT NULL)";

    /**
     * Constructor for a database helper when creating and grabbing
     * data for any of the stored data throughout the app for reviews info
     *
     * @param context
     */
    public ReviewDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates the SQLite database using the TABLE_CREATE string query from above.
     *
     * @param db the SQLite database to be handled for these methods
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    /**
     * Inserts a new review into the Review table in the database.
     * Each "review" row has attributes - imdbID, username, major, rating,
     * and comments.
     *
     * @param review
     */
    public void insertReview(Review review) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_IMDBID, review.getImdbID());
        values.put(COLUMN_TITLE, review.getTitle());
        values.put(COLUMN_POSTER, review.getPoster());
        values.put(COLUMN_USERNAME, review.getUsername());
        values.put(COLUMN_MAJOR, review.getMajor());
        values.put(COLUMN_RATING, review.getRating());
        values.put(COLUMN_COMMENTS, review.getComments());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Searches users.db for a certain username, looks at that username's row
     * of data and pulls his/her name.
     *
     * @param imdbID the given movie we will search the database for (by imdbID)
     * @return a string value of the name of the user associated with a
     * specific username
     */
    public ArrayList<Review> searchReviews(String imdbID) {
        ArrayList<Review> results = new ArrayList<>();
        db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " where imdbID = ?";
        Cursor cursor = db.rawQuery(query, new String[] {imdbID});
        String comments, username, major;
        Float rating;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Review currReview = new Review();

                    comments = cursor.getString(cursor.getColumnIndex(COLUMN_COMMENTS));
                    username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                    major = cursor.getString(cursor.getColumnIndex(COLUMN_MAJOR));
                    rating = cursor.getFloat(cursor.getColumnIndex(COLUMN_RATING));

                    // creating an instance of a review to be added to our total reviews arraylist
                    currReview.setComments(comments);
                    currReview.setUsername(username);
                    currReview.setMajor(major);
                    currReview.setRating(rating);

                    results.add(currReview);
                }
                while(cursor.moveToNext());
            }
            db.close();
            return results;
        } else {
            db.close();
            return null;
        }
    }

    /**
     * Searches reviews.db for all reviews made by someone of a certain major
     * and then grabs that movie information and the average MWF rating
     *
     * @param major will select all movies reviewed by said major
     * @return an array list of reviews, one review per every distinct movie
     */
    public ArrayList<Review> getMajorRec(String major, String rating) {
        ArrayList<Review> results = new ArrayList<>();
        db = this.getReadableDatabase();
        String query = "SELECT imdbID, title, poster, AVG(rating) AS AvgRating FROM reviews" +
                " WHERE imdbID IN (SELECT imdbID FROM reviews WHERE major = ? AND rating >= ?)" +
                " GROUP BY imdbID";
        Cursor cursor = db.rawQuery(query, new String[] {major, rating});
        String title, imdbID, poster;
        Float avgRating;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Review currReview = new Review();

                    title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                    imdbID = cursor.getString(cursor.getColumnIndex(COLUMN_IMDBID));
                    poster = cursor.getString(cursor.getColumnIndex(COLUMN_POSTER));
                    avgRating = cursor.getFloat(cursor.getColumnIndex("AvgRating"));

                    // creating an instance of a review to be added to our total reviews arraylist
                    currReview.setTitle(title);
                    currReview.setImdbID(imdbID);
                    currReview.setPoster(poster);
                    currReview.setRating(avgRating);

                    results.add(currReview);
                }
                while(cursor.moveToNext());
            }
            db.close();
            return results;
        } else {
            db.close();
            return null;
        }
    }


    /**
     * Searches reviews.db for all reviews made by a certain user
     * and then grabs that review information
     *
     * @param username will select all reviews (title + comments) reviewed by this user
     * @return an array list of reviews, one review per every distinct movie
     */
    public ArrayList<Review> getUsersReviews(String username) {
        ArrayList<Review> results = new ArrayList<>();
        db = this.getReadableDatabase();
        String query = "SELECT * FROM reviews WHERE username = ?";
        Cursor cursor = db.rawQuery(query, new String[] {username});
        String comments, major;
        Float rating;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Review currReview = new Review();

                    comments = cursor.getString(cursor.getColumnIndex(COLUMN_COMMENTS));
                    major = cursor.getString(cursor.getColumnIndex(COLUMN_MAJOR));
                    rating = cursor.getFloat(cursor.getColumnIndex(COLUMN_RATING));

                    // creating an instance of a review to be added to our total reviews arraylist
                    currReview.setUsername(username);
                    currReview.setComments(comments);
                    currReview.setMajor(major);
                    currReview.setRating(rating);

                    results.add(currReview);
                }
                while(cursor.moveToNext());
            }
            db.close();
            return results;
        } else {
            db.close();
            return null;
        }
    }


    /**
     * Simple update function for the whole database user table. If an update has been
     * made that alters the entire table, like deleting things, the method drops the
     * old table in favor of the new one.
     *
     * @param db the current database
     * @param oldVersion integer representing the oldest version of a table
     * @param newVersion integer representing the newest version of a table
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
