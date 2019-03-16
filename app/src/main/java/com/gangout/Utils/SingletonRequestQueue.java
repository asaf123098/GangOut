package com.gangout.Utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class SingletonRequestQueue {

    private static SingletonRequestQueue mInstance;
    private Context mContext;
    private RequestQueue mRequestQueue;

    private SingletonRequestQueue(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized SingletonRequestQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SingletonRequestQueue(context);
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    public void add(Request<JSONObject> jsonObjectRequest)
    {
        this.mRequestQueue.add(jsonObjectRequest);
    }
}

