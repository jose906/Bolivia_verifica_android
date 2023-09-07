package com.bolivia.verifica.WpModel;

public class Thumbnail {

    String file;
    int width;
    int height;
    int filesize;
    String mime_type;
    String source_url;

    public Thumbnail(String file, int width, int height, int filesize, String mime_type, String source_url) {
        this.file = file;
        this.width = width;
        this.height = height;
        this.filesize = filesize;
        this.mime_type = mime_type;
        this.source_url = source_url;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFilesize() {
        return filesize;
    }

    public void setFilesize(int filesize) {
        this.filesize = filesize;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }
}
