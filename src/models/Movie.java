package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {

    private String title;
    private int prodYear;
    private String keyWords;
    private ArrayList<Actor> actors;

    public Movie(String title, int prodYear, String keyWords) {
        this.title = title;
        this.prodYear = prodYear;
        this.keyWords = keyWords;
        this.actors = new ArrayList<>();

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProdYear() {
        return prodYear;
    }

    public void setProdYear(int prodYear) {
        this.prodYear = prodYear;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }

    public void addNewActor(Actor actorToAdd) {
        actors.add(actorToAdd);
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }


    @Override
    public String toString() {
        return "Title: " + this.title + ",\nProduction year: " + this.prodYear + "\nKeywords: " + this.keyWords;
    }
}
