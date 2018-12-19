package com.android.wipro.assignment.model;

/**
 * A pojo class for list row being used by retrofit to parse the json response.
 */
public class Row {

    private String title;
    private String description;
    private String imageHref;

    public String getDescription() {
        return description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
