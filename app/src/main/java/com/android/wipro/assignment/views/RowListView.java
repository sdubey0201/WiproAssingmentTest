package com.android.wipro.assignment.views;

import com.android.wipro.assignment.model.Row;

import java.util.List;

/**
 * This interface is supplied to {@link com.android.wipro.assignment.presenters.RowsPresenter}
 * to update the view and is implemented by {@link MainActivity}
 */
public interface RowListView  {
    /**
     * show loading view
     */
    void showLoading();

    /**
     * hide loading view when finish load or exception
     */
    void hideLoading();

    /**
     * show error message
     * @param msg
     */
    void showError(String msg);

    /**
     * show list item
     * @param list
     */
    void showResult(List<Row> list);

    /**
     * update action bar title
     * @param title
     */
    void showTitle(String title);

}
