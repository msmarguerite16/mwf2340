package com.example.staceefreeman.movieswithfriends;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_MAJOR = "major";
    private static final String COLUMN_INTERESTS = "interests";
    private static final String COLUMN_STATUS = "status";

    SQLiteDatabase db;
    private static final String TABLE_CREATE = "CREATE TABLE users (name TEXT NOT NULL, "
            + "email TEXT NOT NULL, username TEXT PRIMARY KEY NOT NULL, password TEXT NOT NULL, "
            + "major TEXT NOT NULL, interests TEXT NOT NULL, status TEXT DEFAULT Active)";

    /**
     * Constructor for a database helper when creating and grabbing
     * data for any of the stored data throughout the app for user info
     *
     * @param context
     */
    public UserDatabaseHelper(Context context) {
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
     * Inserts a new user into the User table in the database
     *
     * @param user
     */
    public void insertUser(User user) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_MAJOR, "");
        values.put(COLUMN_INTERESTS, "");
        values.put(COLUMN_STATUS, "Active");

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    /**
     * Checks to see if a username is available or if it is already in use.
     *
     * @param username
     * @return boolean true or false indicating whether username is
     * available or not
     */
    public boolean isAvailable (String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT " + COLUMN_USERNAME
                + " FROM users WHERE " + COLUMN_USERNAME + " = ?", new String[] {username});
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Edits a user's information based on his/her input by writing
     * the changes to the database, using SQLite's .update() command.
     *
     * @param user the user whose row in the database we will be editing.
     */
    public void editUserProfile(User user) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_MAJOR, user.getMajor());
        values.put(COLUMN_INTERESTS, user.getInterests());

        db.update(TABLE_NAME, values, "username = ?", new String[] {user.getUsername()});

        db.close();
    }

    /**
     * Edits a user's status based on admins input by writing
     * the changes to the database, using SQLite's .update() command.
     *
     * @param username the username whose status we will be updating.
     */
    public void editUserStatus(String username, String status) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_STATUS, status);

        //this isn't correct because we only need to update the STATUS with the username using a query
        db.update(TABLE_NAME, values, "username = ?", new String[] {username});

        db.close();
    }

    /**
     * Searches users.db for a certain username, looks at that username's row
     * of data and pulls his/her name.
     *
     * @param username the given username we will search the database for
     * @return a string value of the name of the user associated with a
     * specific username
     */
    public String searchName(String username) {
        db = this.getReadableDatabase();
        String query = "SELECT username, name FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String uname, name;
        name = "not found";
        if (cursor.moveToFirst()) {
            do {
                uname = cursor.getString(0);
                if (uname.equals(username)) {
                    name = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return name;
    }

    /**
     * Searches users.db for a certain username, looks at that username's row
     * of data and pulls his/her email.
     *
     * @param username the given username we will search the database for
     * @return a string value of the email of the user associated with a
     * specific username
     */
    public String searchEmail(String username) {
        db = this.getReadableDatabase();
        String query = "SELECT username, email FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String uname, email;
        email = "not found";
        if (cursor.moveToFirst()) {
            do {
                uname = cursor.getString(0);
                if (uname.equals(username)) {
                    email = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return email;
    }

    /**
     * Searches users.db for a certain username, looks at that username's row
     * of data and pulls his/her password.
     *
     * @param username the given username we will search the database for
     * @return a string value of the password of the user associated with a
     * specific username
     */
    public String searchPassword(String username) {
        db = this.getReadableDatabase();
        String query = "SELECT username, password FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String uname, password;
        password = "not found";
        if (cursor.moveToFirst()) {
            do {
                uname = cursor.getString(0);
                if (uname.equals(username)) {
                    password = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return password;
    }

    /**
     * Searches users.db for a certain username, looks at that username's row
     * of data and pulls his/her major.
     *
     * @param username the given username we will search the database for
     * @return a string value of any given user's major
     */
    public String searchMajor(String username) {
        db = this.getReadableDatabase();
        String query = "SELECT username, major FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String uname, major;
        major = "not found";
        if (cursor.moveToFirst()) {
            do {
                uname = cursor.getString(0);
                if (uname.equals(username)) {
                    major = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return major;
    }

    /**
     * Searches users.db for a certain username, looks at that username's row
     * of data and pulls his/her interests.
     *
     * @param username the given username we will search the database for
     * @return a string value of any given user's interests
     */
    public String searchInterests(String username) {
        db = this.getReadableDatabase();
        String query = "SELECT username, interests FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String uname, interests;
        interests = "not found";
        if (cursor.moveToFirst()) {
            do {
                uname = cursor.getString(0);
                if (uname.equals(username)) {
                    interests = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return interests;
    }

    /**
     * Searches users.db for a certain username, looks at that username's row
     * of data and pulls his/her status.
     *
     * @param username the given username we will search the database for
     * @return a string value of any given user's status
     */
    public String searchStatus(String username) {
        db = this.getReadableDatabase();
        String query = "SELECT username, status FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String uname, status;
        status = "not found";
        if (cursor.moveToFirst()) {
            do {
                uname = cursor.getString(0);
                if (uname.equals(username)) {
                    status = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return status;
    }

    /**
     * Searches users.db for a certain username, looks at that username's row
     * of data and pulls his/her status.
     *
     * @return a hashmap of all usernames and status'
     */
    public ArrayList<HashMap<String, String>> adminSearch() {
        db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        // array list of hash maps, each hash map is for one user's info
        ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
        String username, status;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> currUser = new HashMap<>();
                    username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                    status = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS));

                    // puts the username to a key "username"
                    // and the user's status to a key "status"
                    currUser.put("username", username);
                    currUser.put("status", status);

                    // adds that single hashmap of a single user's info to the array list
                    results.add(currUser);
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
