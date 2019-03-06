package com.gangout;

import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DBUtils {

    public static void RegisterNewUser(String username, String password, RequestQueue requestQueue) {
        String url = String.format("http://%s/add_user.php", RegisterScreen.ipAddress);
        Log.d("ip", url);
        CustomRequest registerUser;

        Map<String, String> user_credentials = new HashMap<String, String>();
        user_credentials.put("username", username);
        user_credentials.put("password", password);

        registerUser = new CustomRequest(Request.Method.POST, url, user_credentials, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("user", response.getString("username"));
                    Log.d("password", response.getString("password"));
                }
                catch (JSONException e)
                {
                    Log.e("Response json exception", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response error: ", error.toString());
            }
        });

        requestQueue.add(registerUser);
    }


}
