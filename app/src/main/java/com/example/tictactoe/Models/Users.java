package com.example.tictactoe.Models;

public class Users {
    String userName,eMail,password,userId,profilePic;

    public Users(String userName, String eMail, String password, String userId, String profilePic) {
        this.userName = userName;
        this.eMail = eMail;
        this.password = password;
        this.userId = userId;
        this.profilePic = profilePic;
    }

    public Users(){}
    //signup constructor

    public Users(String userName, String eMail, String password) {
        this.userName = userName;
        this.eMail = eMail;
        this.password = password;
    }

    //signup const end

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
