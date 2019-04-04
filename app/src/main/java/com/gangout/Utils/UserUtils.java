package com.gangout.Utils;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.gangout.RegisterScreen;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserUtils {

    private ServerResponse responseNotifier;
    private boolean serverResponded;
    private static boolean userExists;

    public static void RegisterNewUser(String username, String password) {
        String url = String.format("http://%s/add_user.php", RegisterScreen.ipAddress);
        Log.d("ip", url);

        Map<String, String> user_credentials = new HashMap<String, String>();
        user_credentials.put("username", username);
        user_credentials.put("password", password);
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("user", response.getString("username"));
                    Log.d("password", response.getString("password"));
                } catch (JSONException e) {
                    Log.e("Response json exception", e.getMessage());
                }
            }
        };
        DBUtils.sendRequest(Request.Method.POST, url, user_credentials, listener);
    }

    static void DoesUserExist(String username, RequestQueue requestQueue) {
        String url = String.format("http://%s/verify_user_existance.php", RegisterScreen.ipAddress);

        Map<String, String> user_credentials = new HashMap<String, String>();
        user_credentials.put("username", username);
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("exists"))
                    {
//                        userExists =
                        int a = 1;
                    }


                } catch (JSONException e) {
                    Log.e("Response json exception", e.getMessage());
                }
            }
        };

        DBUtils.sendRequest(Request.Method.POST, url, user_credentials, listener);

    }
}
