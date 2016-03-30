package com.example.staceefreeman.movieswithfriends;

public class User {
    String name, email, username, password, major, interests, status;

    /**
     * Simple getter function for the String name variable
     * associated with each user.
     *
     * @return the name of the user (first and last)
     */
    public String getName() {
        return name;
    }

    /**
     * Simple setter method for a user's name.
     *
     * @param name string variable of a user's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Simple getter function for the String email variable
     * associated with each user.
     *
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Simple setter method for a user's email.
     *
     * @param email string variable of a user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Simple getter function for the String email variable
     * associated with each user.
     *
     * @return the user's email
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
     * Simple getter function for the String password variable
     * associated with each user.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Simple setter method for a user's password.
     *
     * @param password string variable of a user's password.
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Simple getter function for the String interests variable
     * associated with each user.
     *
     * @return the user's interests, could be any format as
     * long as it's a string
     */
    public String getInterests() {
        return interests;
    }

    /**
     * Simple setter method for a user's interests.
     *
     * @param interests string variable of a user's inputted interests.
     */
    public void setInterests(String interests) {
        this.interests = interests;
    }

    /**
     * Simple getter function for the String status variable
     * associated with each user.
     *
     * @return the user's status, could be any format as
     * long as it's a string
     */
    public String getStatus() {
        return status;
    }

    /**
     * Simple setter method for a user's status.
     *
     * @param status string variable of a user's status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
