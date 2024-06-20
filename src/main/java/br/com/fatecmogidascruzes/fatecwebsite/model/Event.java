package br.com.fatecmogidascruzes.fatecwebsite.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private String title;
    private String time;
    private String description;
    private String date; // format: "yyyy-MM-dd"

    // Default constructor
    public Event() {}

    // Parameterized constructor
    public Event(String id, String title, String time, String description, String date) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.description = description;
        this.date = date;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
