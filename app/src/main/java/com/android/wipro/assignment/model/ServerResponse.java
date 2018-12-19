package com.android.wipro.assignment.model;

import java.util.List;

/**
 * This class being used by retrofit to parse the response
 */
public class ServerResponse {
    /**
     * These members will be used by Retrofit, so we must use the same name with json
     */
    private String title;
    private List<Row> rows;

    public List<Row> getRows() {
        return rows;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
