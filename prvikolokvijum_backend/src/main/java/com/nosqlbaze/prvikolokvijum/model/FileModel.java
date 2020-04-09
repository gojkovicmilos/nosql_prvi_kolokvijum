package com.nosqlbaze.prvikolokvijum.model;


import java.io.InputStream;

public class FileModel {
    private String title;
    private InputStream stream;
    
    public FileModel() {
        super();
    }

    public FileModel(String title) {
        super();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    @Override
    public String toString() {
        return "File [title=" + title + "]";
    }

}