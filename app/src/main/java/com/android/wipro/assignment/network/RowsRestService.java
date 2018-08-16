package com.android.wipro.assignment.network;

import com.android.wipro.assignment.model.ServerResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * This interface is passed to retrofit to create rest adapter
 */
public interface RowsRestService {
    final String SERVICE_ENDPOINT = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json/";
    /**
     * Use Retrofit to get JSON from URL.
     * @return Observable
     */
    @GET(".")
    Observable<ServerResponse> getCountry();


}
