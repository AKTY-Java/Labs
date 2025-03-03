package edu.ucalgary.oop;

import java.util.Objects;

public class Task {
    private String id;
    private String title;
    private boolean isCompleted;

    // Constructor used in tests (sets isCompleted to false by default)
    public Task(String id, String title) {
        this.id = id;
        this.title = title;
        this.isCompleted = false;
    }

    // Optional constructor if you want to initialize isCompleted
    public Task(String id, String title, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    // Deep copy method
    public Task copy() {
        return new Task(this.id, this.title, this.isCompleted);
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    // The equals and hashCode methods are from the original stub.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return isCompleted == task.isCompleted &&
                Objects.equals(id, task.id) &&
                Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isCompleted);
    }
}
