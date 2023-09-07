package com.bolivia.verifica.WpModel;

public class Posts {
    int id;
    String date;
    String date_gmt;
    Guid guid;
    String modified;
    String modified_gmt;
    String slug;
    String status;
    String type;
    String link;
    Title title;
    Content content;
    int autor;
    int featured_media;
    String comment_status;
    String ping_status;
    String sticky;
    String template;
    String format;
    int[] categories;
    int[] tags;
    int page;
    String code;


    public Posts(int id, String date, String date_gmt, Guid guid, String modified, String modified_gmt, String slug, String status, String type, String link, Title title, Content content, int autor, int featured_media, String comment_status, String ping_status, String sticky, String template, String format, int[] categories, int[] tags, int page, String code) {
        this.id = id;
        this.date = date;
        this.date_gmt = date_gmt;
        this.guid = guid;
        this.modified = modified;
        this.modified_gmt = modified_gmt;
        this.slug = slug;
        this.status = status;
        this.type = type;
        this.link = link;
        this.title = title;
        this.content = content;
        this.autor = autor;
        this.featured_media = featured_media;
        this.comment_status = comment_status;
        this.ping_status = ping_status;
        this.sticky = sticky;
        this.template = template;
        this.format = format;
        this.categories = categories;
        this.tags = tags;
        this.page = page;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_gmt() {
        return date_gmt;
    }

    public void setDate_gmt(String date_gmt) {
        this.date_gmt = date_gmt;
    }

    public Guid getGuid() {
        return guid;
    }

    public void setGuid(Guid guid) {
        this.guid = guid;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getModified_gmt() {
        return modified_gmt;
    }

    public void setModified_gmt(String modified_gmt) {
        this.modified_gmt = modified_gmt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public int getFeatured_media() {
        return featured_media;
    }

    public void setFeatured_media(int featured_media) {
        this.featured_media = featured_media;
    }

    public String getComment_status() {
        return comment_status;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public String getPing_status() {
        return ping_status;
    }

    public void setPing_status(String ping_status) {
        this.ping_status = ping_status;
    }

    public String getSticky() {
        return sticky;
    }

    public void setSticky(String sticky) {
        this.sticky = sticky;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int[] getCategories() {
        return categories;
    }

    public void setCategories(int[] categories) {
        this.categories = categories;
    }

    public int[] getTags() {
        return tags;
    }

    public void setTags(int[] tags) {
        this.tags = tags;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
