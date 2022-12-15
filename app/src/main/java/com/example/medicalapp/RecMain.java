package com.example.medicalapp;

public class RecMain {
    private String title;
    private String description;
    private int img;
    private String fullDescription;

    public RecMain(String title, String description, int img, String fullDescription) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.fullDescription = fullDescription;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }
}
