package com.example.staceefreeman.movieswithfriends;

public class Movie {

    String title, year, rated, released, runtime, genre, director,
            writer, actors, plot, imdbID, imdbRating, poster;

    /**
     * Simple getter function for the String title variable
     * associated with a given movie.
     *
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }


    /**
     * Simple setter method for a movie's title.
     *
     * @param title the title of the movie
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Simple getter function for the String year variable
     * associated with a given movie.
     *
     * @return the release year of the movie
     */
    public String getYear() {
        return year;
    }


    /**
     * Simple setter method for a movie's release year.
     *
     * @param year the year the movie was released
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Simple getter function for the String rating variable
     * associated with a given movie.
     *
     * @return the rating of a movie (G, PG, PG13, R)
     */
    public String getRated() {
        return rated;
    }


    /**
     * Simple setter method for a movie's rating (G, PG, R).
     *
     * @param rated the title of the movie
     */
    public void setRated(String rated) {
        this.rated = rated;
    }

    /**
     * Simple getter function for the String released variable
     * associated with a given movie.
     *
     * @return the official release date of the movie
     */
    public String getReleased() {
        return released;
    }


    /**
     * Simple setter method for a movie's release date.
     *
     * @param released the movie's official release date
     */
    public void setReleased(String released) {
        this.released = released;
    }

    /**
     * Simple getter function for the String runtime variable
     * associated with a given movie.
     *
     * @return the movie's total runtime in minutes (as a string)
     */
    public String getRuntime() {
        return runtime;
    }


    /**
     * Simple setter method for a movie's runtime.
     *
     * @param runtime the total runtime of the movie, in minutes (but as a string)
     */
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    /**
     * Simple getter function for the String genre variable
     * associated with a given movie.
     *
     * @return the movie's genres (can be more than one, just separated
     * by a comma in the string)
     */
    public String getGenre() {
        return genre;
    }


    /**
     * Simple setter method for a movie's genres.
     *
     * @param genre the various genres of the movie
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Simple getter function for the String director variable
     * associated with each user.
     *
     * @return the name of the movie's director (first and last)
     */
    public String getDirector() {
        return director;
    }


    /**
     * Simple setter method for a movie's director.
     *
     * @param director the movie's director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Simple getter function for the String writer variable
     * associated with each user.
     *
     * @return the name of the movie's writer(s) (first and last)
     */
    public String getWriter() {
        return writer;
    }


    /**
     * Simple setter method for a movie's writer(s).
     *
     * @param writer the movie's writer(s)
     */
    public void setWriter(String writer) {
        this.writer = writer;
    }

    /**
     * Simple getter function for the String actors variable
     * associated with each user.
     *
     * @return the name of the movie's actors (first and last, each separated
     * by a comma within the string)
     */
    public String getActors() {
        return actors;
    }


    /**
     * Simple setter method for a movie's title.
     *
     * @param actors the actors of the movie
     */
    public void setActors(String actors) {
        this.actors = actors;
    }

    /**
     * Simple getter function for the String plot variable
     * associated with each user.
     *
     * @return a string of the movie's plot (short plot, very brief)
     */
    public String getPlot() {
        return plot;
    }


    /**
     * Simple setter method for a movie's plot.
     *
     * @param plot the plot of the movie, short
     */
    public void setPlot(String plot) {
        this.plot = plot;
    }

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
     * Simple getter function for the String imdbRating variable
     * associated with each user.
     *
     * @return the movie's imdbRating (between 1 and 10, as a string)
     */
    public String getImdbRating() {
        return imdbRating;
    }


    /**
     * Simple setter method for a movie's imdb rating.
     *
     * @param imdbRating the imdb rating of the movie (1 through 10,
     *                   but in the form of a string)
     */
    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    /**
     * Simple getter function for the String poster variable
     * associated with each user.
     *
     * @return the poster of the movie (in the form of a URL)
     */
    public String getPoster() {
        return poster;
    }


    /**
     * Simple setter method for a movie's poster url.
     *
     * @param poster the movie's poster, in the form of a url link
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }
}
