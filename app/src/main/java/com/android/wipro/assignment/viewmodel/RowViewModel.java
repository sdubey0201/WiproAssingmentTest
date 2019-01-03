package com.android.wipro.assignment.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.android.wipro.assignment.model.IRowsDataListener;
import com.android.wipro.assignment.model.ServerResponse;
import com.android.wipro.assignment.network.RowsRetrofitClient;

/**
 * This is viewModel class of MainActivity.
 * This class is responsible for presenting the data to view layer by setting data in @MutableLiveData of type Server response once we call set method on mutable live date it will call onChange method of obeserver
 * This class also interact with the model layer to download the data from server by calling {@link RowsRetrofitClient}
 * The model layer return the data back by calling {@link IRowsDataListener} method(onError and onRowsResultReceived)
 */

public class RowViewModel extends ViewModel implements IRowsDataListener {
    //this is the data that we will fetch asynchronously
    private MutableLiveData<ServerResponse> listMutableLiveData = new MutableLiveData<>();
    private RowsRetrofitClient rowsRetrofitClient;

    public void startLoadRows() {
        if (rowsRetrofitClient == null ) {
            rowsRetrofitClient = new RowsRetrofitClient();
            rowsRetrofitClient.callRowsData(this);
        }


    }

    public void refreshRowList() {
        rowsRetrofitClient.callRowsData(this);
    }


    @Override
    public void onRowsResultReceived(ServerResponse serverResponse) {
        listMutableLiveData.setValue(serverResponse);

    }

    @Override
    public void onError(String s) {
        listMutableLiveData.setValue(null);
    }


    public MutableLiveData<ServerResponse> getListMutableLiveData() {
        return listMutableLiveData;
    }
}
