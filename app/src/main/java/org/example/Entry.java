package org.example;
/*
 * Object that holds data for to-do list entry, with a title, description, priority
 */

public class Entry {

    protected String title, description;
    protected int priority;
    protected boolean completed;
    protected String dateCreated, dateDue, dateCompleted;

    public Entry(String title, String description, int priority, String dateCreated, String dateDue) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dateCreated = dateCreated;
        this.dateDue = dateDue;
        this.completed = false;

        // if entry is empty throw exception
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Entry cannot be empty");
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPriority(int priority) {
        // check if priority is valid
        if (priority < 0 || priority > 10) {
            throw new IllegalArgumentException("Priority must be between 0 and 10");
        } else {
            this.priority = priority;
        }
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }
    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
    
    public String[] getAsArray() {
        return new String[] {title, description, String.valueOf(priority), String.valueOf(completed), dateCreated, dateDue, dateCompleted};
    }

    @Override
    public String toString() {
        return "==========================================\n" +
                "title : " + title + '\n' +
                "description : " + description + '\n' +
                "priority : " + priority + '\n' +
                "completed : " + completed + '\n' +
                "dateCreated : " + dateCreated + '\n' +
                "dateDue : " + dateDue + '\n' +
                "dateCompleted : " + dateCompleted + '\n' +
                "==========================================\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Entry)) return false;
        Entry entry = (Entry) obj;
        return priority == entry.priority &&
                completed == entry.completed &&
                title.equals(entry.title) &&
                description.equals(entry.description) &&
                dateCreated.equals(entry.dateCreated) &&
                dateDue.equals(entry.dateDue) &&
                dateCompleted.equals(entry.dateCompleted);
    }
}