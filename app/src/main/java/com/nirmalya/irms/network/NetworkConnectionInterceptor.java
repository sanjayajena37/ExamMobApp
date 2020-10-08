package com.nirmalya.irms.network;

import android.content.Context;


import com.nirmalya.irms.utility.Utils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkConnectionInterceptor implements Interceptor {

    private Context context;

    public NetworkConnectionInterceptor(Context context) {
        this.context = context;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        // Throwing our custom exception 'NoConnectivityException'
        if (!Utils.isNetworkAvailable(context, true))
            throw new NoConnectivityException();

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

    public static class NoConnectivityException extends IOException {

        @Override
        public String getMessage() {
            return "No Internet Connection";
            // You can send any message whatever you want from here.
        }
    }
}