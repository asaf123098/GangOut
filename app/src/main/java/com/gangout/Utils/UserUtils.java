package com.gangout.Utils;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.gangout.RegisterScreen;

import java.util.HashMap;
import java.util.Map;

public class UserUtils {

    public static void RegisterNewUser(String username, String password, RequestQueue requestQueue) {
        String url = String.format("http://%s/add_user.php", RegisterScreen.ipAddress);
        Log.d("ip", url);

        Map<String, String> user_credentials = new HashMap<String, String>();
        user_credentials.put("username", username);
        user_credentials.put("password", password);
        DBUtils.sendRequest(Request.Method.POST, url, user_credentials, requestQueue);
    }
}
