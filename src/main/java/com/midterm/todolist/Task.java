package com.midterm.todolist;

public class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void markComplete() {
        this.isCompleted = true;
    }

    public String getDescription() {  // ✅ Getter for description
        return description;
    }

    public boolean isCompleted() {  // ✅ Getter for completion status
        return isCompleted;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[✔] " : "[ ] ") + description;
    }
}
