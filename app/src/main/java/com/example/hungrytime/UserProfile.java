package com.example.hungrytime;

public class UserProfile {
    public String userName;
    public String userEmail;

    public UserProfile(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public UserProfile(){

    }

    public UserProfile(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
