package com.nirmalya.governmentexams.network;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nirmalya.governmentexams.utility.Constant;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static final int TIME_OUT = 1;

    private Context context;

    private Gson gson = null;

    @SuppressLint("StaticFieldLeak")
    private static ApiManager apiManager;

    private ApiManager(Context context) {
        this.context = context;
    }

    public static ApiManager getInstance(Context context) {
        if (apiManager == null) {
            apiManager = new ApiManager(context);
        }
        return apiManager;
    }

    public ApiInterface createService() {
        return createService(null);
    }

    public ApiInterface createService(HashMap<String, String> header) {
        if (gson == null)
            gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit =
                new Retrofit
                        .Builder()
                        .baseUrl(Constant.BASE_URL)
                        .client(getOkHttClient(header))
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();

        return retrofit.create(ApiInterface.class);
    }

    private OkHttpClient getOkHttClient(final HashMap<String, String> header) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(TIME_OUT, TimeUnit.MINUTES);
        okHttpClient.writeTimeout(TIME_OUT, TimeUnit.MINUTES);
        okHttpClient.readTimeout(TIME_OUT, TimeUnit.MINUTES);

        //if (BuildConfig.DEBUG) {
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        //}

        okHttpClient.addInterceptor(new NetworkConnectionInterceptor(context));

        okHttpClient.addInterceptor(chain -> {

            Request.Builder newRequest = chain.request().newBuilder();
            newRequest.header(Constant.KEY_CONTENT_TYPE, Constant.KEY_APPLICATION_JSON);
            if (header != null) {
                for (String key : header.keySet())
                    newRequest.header(key, Objects.requireNonNull(header.get(key)));
            }

            return chain.proceed(newRequest.build());
        });

        return okHttpClient.build();
    }

}
