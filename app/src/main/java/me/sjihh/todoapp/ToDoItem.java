package me.sjihh.todoapp;

import androidx.annotation.NonNull;

public class ToDoItem {
    private int id;
    private String title;
    private String description;
    private long dueDate;


    public ToDoItem(String title, String description, long dueDate) {
        this.description = description;
        this.title = title;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public long getDueDate() {
        return dueDate;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    @Override
    public String toString() {
        return "ToDoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}