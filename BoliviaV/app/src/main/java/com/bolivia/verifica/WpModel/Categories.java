package com.bolivia.verifica.WpModel;

public class Categories {

    int id;
    int count;
    String description;
    String link;
    String name;
    String slug;
    String taxonomy;
    int parent;

    public Categories(int id, int count, String description, String link, String name, String slug, String taxonomy, int parent) {
        this.id = id;
        this.count = count;
        this.description = description;
        this.link = link;
        this.name = name;
        this.slug = slug;
        this.taxonomy = taxonomy;
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}
