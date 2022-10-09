package com.example.devedbaseproject.models;

import java.util.ArrayList;

public class TagWrapper {

    private ArrayList<Tag> tagList;

    public TagWrapper() {
        this.tagList = new ArrayList<>();
    }

    public TagWrapper(ArrayList<Tag> tagList) {
        this.tagList = tagList;
    }

    public void setTagList(ArrayList<Tag> tagList) {
        this.tagList = tagList;
    }

    public ArrayList<Tag> getTagList() {
        return tagList;
    }
}
