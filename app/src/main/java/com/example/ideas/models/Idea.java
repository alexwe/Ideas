package com.example.ideas.models;

import java.io.Serializable;

/**
 * Model for ideas, including a label, rating, and the idea string.
 * @author Alex Weckiewicz
 */
public class Idea implements Serializable {
    private String idea;
    private String label;
    private int rating;


    public Idea(String idea) {
        this.idea = idea;
    }

    public Idea(String idea, String label, int rating) {
        this.idea = idea;
        this.label = label;
        this.rating = rating;
    }

    public String getIdea() { return idea; }

    public void setIdea(String idea) { this.idea = idea; }

    public String getLabel() { return label; }

    public void setLabel(String label) { this.label = label; }

    public int getRating() { return rating; }

    public void setRating(int rating) { this.rating = rating; }

    @Override
    public String toString() {
        return this.getIdea();
    }

}
