package com.gangout;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterScreen extends Activity implements View.OnClickListener {

    private EditText userName;
    private EditText passWord;
    private Button registerButton;

    private RequestQueue requestQueue;
    private JsonObjectRequest registerUser;
    private JSONObject user_credentials = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.userName = (EditText) findViewById(R.id.register_username);
        this.passWord = (EditText) findViewById(R.id.register_password);
        this.registerButton = (Button) findViewById(R.id.register_button);
        this.registerButton.setOnClickListener(this);

        this.requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.26/add_user.php";

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        try {
                            Toast.makeText(getApplicationContext(), response.getString("name"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("Exception in JSONNNN: ", e.getMessage());
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                });

        registerUser = new JsonObjectRequest(Request.Method.POST, url, this.user_credentials, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i("tostring***", response.toString());
                    Toast.makeText(getApplicationContext(), "Username is: " + response.getString("username"), Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Password is: " + response.getString("password"), Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    Log.e("Caught exception: ", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response error: ", "error message: " + error.toString());
                Log.d("Response error: ", "error message: " + error.getMessage());
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        Intent goToRegisterIntent = new Intent(this, WelcomeScreen.class);
        startActivity(goToRegisterIntent, bundle);
        return true;

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == registerButton.getId())
        {
            String username = this.userName.getText().toString();
            String password = this.passWord.getText().toString();
            try {
                this.user_credentials.put("username", username);
                this.user_credentials.put("password", password);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), "Registering...", Toast.LENGTH_SHORT).show();
            this.requestQueue.add(registerUser);
        }
    }
}
