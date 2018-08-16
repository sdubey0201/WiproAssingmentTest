package com.android.wipro.assignment.network;


import com.android.wipro.assignment.BuildConfig;
import com.android.wipro.assignment.model.IRowsDataListener;
import com.android.wipro.assignment.model.Rows;
import com.android.wipro.assignment.model.ServerResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = Config.NONE)
public class RowsRetrofitClientTest {

    private RowsRetrofitClient rowRetrofitClient;
    IRowsDataListener iRowsDataListener;
    Retrofit retrofit;
    RowsRestService rowsRestService;

    @Before
    public void setUp() throws Exception {
        rowRetrofitClient = new RowsRetrofitClient();
        iRowsDataListener = mock(IRowsDataListener.class);
        rowsRestService = mock(RowsRestService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void callRowsData() {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setTitle("title");
        List<Rows> list = new ArrayList<>();
        Rows rows = new Rows();
        rows.setTitle("title row");
        rows.setDescription("description");
        rows.setImageHref("url");
        list.add(rows);
        serverResponse.setRows(list);
        when(rowsRestService.getCountry()).thenReturn(rx.Observable.just(serverResponse));
        rowRetrofitClient.callRowsData(iRowsDataListener);
        verify(iRowsDataListener).onRowsResultReceived(serverResponse);


    }
}