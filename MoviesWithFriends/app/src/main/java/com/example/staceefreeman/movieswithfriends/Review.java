package com.example.staceefreeman.movieswithfriends;

public class Review {
    String imdbID, username, major, comments, title, poster;
    Float rating;

    /**
     * Simple getter function for the String imdbID variable
     * associated with each user.
     *
     * @return the imdbID of the movie
     */
    public String getImdbID() {
        return imdbID;
    }


    /**
     * Simple setter method for a movie's imdbID.
     *
     * @param imdbID the unique imdbID of the movie
     */
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    /**
     * Simple getter function for the String title variable
     * associated with each movie.
     *
     * @return the user's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Simple setter method for a movie's title.
     *
     * @param title string variable of a movie's title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Simple getter function for the String poster variable
     * associated with each movie.
     *
     * @return the user's poster
     */
    public String getPoster() {
        return poster;
    }

    /**
     * Simple setter method for a movie's poster url.
     *
     * @param poster string variable of a movie's poster url.
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }

    /**
     * Simple getter function for the String username variable
     * associated with each user.
     *
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Simple setter method for a user's username.
     *
     * @param username string variable of a user's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Simple getter function for the String major variable
     * associated with each user.
     *
     * @return the user's email
     */
    public String getMajor() {
        return major;
    }

    /**
     * Simple setter method for a user's major.
     *
     * @param major string variable of a user's major, as selected
     *              from the scroll menu on EditProfile.java
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Simple getter function for the String rating variable
     * associated with each user.
     *
     * @return the movie's rating by user (between 1 and 5)
     */
    public Float getRating() {
        return rating;
    }


    /**
     * Simple setter method for a movie's rating.
     *
     * @param rating the rating of the movie by user (1 through 5)
     */
    public void setRating(Float rating) {
        this.rating = rating;
    }

    /**
     * Simple getter function for the String review variable
     * associated with each user and movie.
     *
     * @return the movie's review by user
     */
    public String getComments() {
        return comments;
    }


    /**
     * Simple setter method for a movie's review.
     *
     * @param comments the review of the movie by user
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
}
