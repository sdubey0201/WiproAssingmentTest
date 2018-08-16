package com.android.wipro.assignment.presenters;

import android.view.View;

import com.android.wipro.assignment.BuildConfig;
import com.android.wipro.assignment.model.IRowsDataListener;
import com.android.wipro.assignment.model.Rows;
import com.android.wipro.assignment.model.ServerResponse;
import com.android.wipro.assignment.network.RowsRetrofitClient;
import com.android.wipro.assignment.views.RowListView;
import com.android.wipro.assignment.views.RowsAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = Config.NONE)
public class RowsPresenterTest {
    RowListView mockView;
    RowsPresenter rowsPresenter;
    IRowsDataListener iRowsDataListener;
    RowsRetrofitClient rowsRetrofitClient;

    @Before
    public void setUp() throws Exception {
        mockView = mock(RowListView.class);
        iRowsDataListener = mock(IRowsDataListener.class);
        rowsPresenter = new RowsPresenter(mockView);
        rowsRetrofitClient = mock(RowsRetrofitClient.class);

        // mockView = mock(View.class);
    }


    @After
    public void tearDown() throws Exception {
        mockView = null;
        rowsPresenter = null;
        iRowsDataListener = null;
        rowsRetrofitClient = null;

    }


    @Test
    public void startLoadRows() {
        rowsPresenter.startLoadRows();
        verify(mockView).showLoading();
    }

    @Test
    public void onRowsResultReceived() {

        List<Rows> list = new ArrayList<>();
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setRows(list);
        serverResponse.setTitle("title");
        mockView.showResult(list);
        mockView.showTitle(serverResponse.getTitle());
        mockView.hideLoading();
    }

    @Test
    public void onError() {
        mockView.hideLoading();

        mockView.showError("Error message");
    }
}