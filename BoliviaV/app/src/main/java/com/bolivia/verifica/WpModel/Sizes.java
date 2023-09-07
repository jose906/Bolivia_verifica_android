package com.bolivia.verifica.WpModel;

public class Sizes {

    Medium medium;
    Thumbnail thumbnail;
    Medium_Large medium_large;
    Covernews_Featured covernews_featured;
    Full full;

    public Sizes(Medium medium, Thumbnail thumbnail, Medium_Large medium_large, Covernews_Featured covernews_featured, Full full) {
        this.medium = medium;
        this.thumbnail = thumbnail;
        this.medium_large = medium_large;
        this.covernews_featured = covernews_featured;
        this.full = full;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Medium_Large getMedium_large() {
        return medium_large;
    }

    public void setMedium_large(Medium_Large medium_large) {
        this.medium_large = medium_large;
    }

    public Covernews_Featured getCovernews_featured() {
        return covernews_featured;
    }

    public void setCovernews_featured(Covernews_Featured covernews_featured) {
        this.covernews_featured = covernews_featured;
    }

    public Full getFull() {
        return full;
    }

    public void setFull(Full full) {
        this.full = full;
    }
}
