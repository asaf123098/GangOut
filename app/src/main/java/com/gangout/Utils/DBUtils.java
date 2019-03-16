package com.gangout.Utils;

import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gangout.CustomRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class DBUtils {

    public static void sendRequest(int method, String url, Map<String, String> values_map, Response.Listener<JSONObject> responseListener)
    {
        CustomRequest registerUser;

        registerUser = new CustomRequest(method, url, values_map,
                responseListener, DBUtils.getResponseErrorListener());

        SingletonRequestQueue.getInstance(null).add(registerUser);
    }

    private static Response.ErrorListener getResponseErrorListener()
    {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorString;

                if (error.networkResponse != null)
                    Log.d("Response error data", new String(error.networkResponse.data, StandardCharsets.UTF_8));
                else
                    Log.d("Response error message", error.getMessage());

            }
        };
    }
}
