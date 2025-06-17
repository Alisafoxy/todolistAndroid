package com.dtafox.myapplication.data;

/** Simple task model stored in Firestore */
public class Task {
    public String id;
    public String title;
    public String description;

    public Task() {
        // required empty constructor for Firestore
    }

    public Task(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
