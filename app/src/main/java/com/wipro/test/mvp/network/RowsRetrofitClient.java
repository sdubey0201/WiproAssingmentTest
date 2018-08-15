package com.wipro.test.mvp.network;

import android.text.TextUtils;

import com.wipro.test.mvp.model.IRowsDataListener;
import com.wipro.test.mvp.model.Rows;
import com.wipro.test.mvp.model.ServerResponse;

import java.util.Iterator;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * This class is used by {@link com.wipro.test.mvp.presenters.RowsPresenter}
 * to request the data from network.
 */
public class RowsRetrofitClient {

    private Retrofit getRestAdapter() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(RowsRestService.SERVICE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        return restAdapter;

    }

    /**
     * @param iRowsDataListener: used for interacting with the presenter to send the data/error_message
     *                           back to presenter, so that prasenter can update the view.
     */
    public void callRowsData(final IRowsDataListener iRowsDataListener) {
        getRestAdapter().create(RowsRestService.class).getCountry()
                .map(serverResponse -> removeEmptyItemFromResponse(serverResponse))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(serverResponse -> {
                    if (iRowsDataListener == null) {
                        return;
                    }
                    iRowsDataListener.onRowsResultReceived(serverResponse);
                }, throwable -> {
                    if (iRowsDataListener == null) {
                        iRowsDataListener.onError(throwable.getMessage());
                    }
                });
    }

    private ServerResponse  removeEmptyItemFromResponse(ServerResponse serverResponse) {

        List<Rows> list = serverResponse.getRows();
        if (list != null) {
            Iterator<Rows> iterator = list.iterator();
            while (iterator.hasNext()) {
                Rows fact = iterator.next();
                if (TextUtils.isEmpty(fact.getTitle())) {
                    iterator.remove();
                }
            }
        }
        return serverResponse;
    }
}
