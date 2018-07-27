package com.example.student.lab11_listview;

import java.io.Serializable;

class Pokemon implements Serializable{

    private String id;
    private String name;
    private int imageId;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public Pokemon(String id, String name, int imageId){
        this.id = id;
        this.name = name;
        this.imageId = imageId;
    }

}
