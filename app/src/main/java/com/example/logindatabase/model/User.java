package com.example.logindatabase.model;

import com.example.logindatabase.UserActivity;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String timestamp;

    public User(){

    }

    public User(int id, String firstName, String lastName, String email, String password, String timestamp){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.timestamp = timestamp;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getTimestamp(){
        return timestamp;
    }
    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }



    public String toString(){
        return "ID: " + getId() + "First Name: " +getFirstName() + "Email: " + getEmail();
    }

}
