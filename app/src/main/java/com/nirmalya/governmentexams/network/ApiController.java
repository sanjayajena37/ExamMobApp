package com.nirmalya.governmentexams.network;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;





/*
 * common class needs to instantiate before launching of the application
 * which can stabilize the application as a single instance
 */

public class ApiController extends Application {

    public static final String TAG = ApiController.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static ApiController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        /*// Initialize the Branch object
        Branch.getAutoInstance(this);

        // Branch logging for debugging
        Branch.enableLogging();*/

    }

    public static synchronized ApiController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


}