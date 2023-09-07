package com.bolivia.verifica.WpModel;

public class Media {


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
     int author;
     String comment_status;
     String ping_status;
     String media_type;
     String mime_type;
     Media_Details media_details;
     int post;
     String source_url;

     public Media(int id, String date, String date_gmt, Guid guid, String modified, String modified_gmt, String slug, String status, String type, String link, Title title, int author, String comment_status, String ping_status, String media_type, String mime_type, Media_Details media_details, int post, String source_url) {
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
          this.author = author;
          this.comment_status = comment_status;
          this.ping_status = ping_status;
          this.media_type = media_type;
          this.mime_type = mime_type;
          this.media_details = media_details;
          this.post = post;
          this.source_url = source_url;
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

     public int getAuthor() {
          return author;
     }

     public void setAuthor(int author) {
          this.author = author;
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

     public String getMedia_type() {
          return media_type;
     }

     public void setMedia_type(String media_type) {
          this.media_type = media_type;
     }

     public String getMime_type() {
          return mime_type;
     }

     public void setMime_type(String mime_type) {
          this.mime_type = mime_type;
     }

     public Media_Details getMediaDetails() {
          return media_details;
     }

     public void setMediaDetails(Media_Details media_details) {
          this.media_details = media_details;
     }

     public int getPost() {
          return post;
     }

     public void setPost(int post) {
          this.post = post;
     }

     public String getSource_url() {
          return source_url;
     }

     public void setSource_url(String source_url) {
          this.source_url = source_url;
     }
}
