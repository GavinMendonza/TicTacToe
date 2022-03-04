package com.example.tictactoe;

public class User {
    public  String Username;
    public String Email;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public User(){

    }

    public User(String Username, String Email){
        this.Username = Username;
        this.Email = Email;
    }

}
