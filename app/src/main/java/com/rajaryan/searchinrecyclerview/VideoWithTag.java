package com.rajaryan.searchinrecyclerview;

public class VideoWithTag {
    String link,pdf,image,tag;

    public VideoWithTag() {
    }

    public VideoWithTag(String link, String pdf, String image, String tag) {
        this.link = link;
        this.pdf = pdf;
        this.image = image;
        this.tag = tag;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
