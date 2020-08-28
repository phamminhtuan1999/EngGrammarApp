package com.ductho.nguphaptienganh.Model;

public class Note {
    int ID;
    String title, description;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Note(int ID, String title, String description) {
        this.ID = ID;
        this.title = title;
        this.description = description;
    }
     public Note(){};
}
