package com.example.morejump.bussu;

/**
 * Created by morejump on 24/06/2017.
 */

public class User {
    public String name;
    public String email;
    public  int Count;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String email, int count) {
        this.name = name;
        this.email = email;
        this.Count= count;
    }
}
