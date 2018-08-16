package com.android.wipro.assignment.model;

public interface IRowsDataListener {
    void onRowsResultReceived(ServerResponse serverResponse);
    void onError(String s);
}
