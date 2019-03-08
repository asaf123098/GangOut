package com.gangout;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.gangout.Utils.DBUtils;
import com.gangout.Utils.UserUtils;

public class RegisterScreen extends Activity implements View.OnClickListener {

    private EditText userName;
    private EditText passWord;
    private Button registerButton;

    private RequestQueue requestQueue;
    public static String ipAddress = "192.168.2.142:81";

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId()) {
            case R.id.settings_butt:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Please enter the server's ip:");
                builder.setView(R.layout.ip_set_dialog_layout);

                builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Dialog dialog = (Dialog)dialogInterface;
                        EditText ip_box = (EditText) dialog.findViewById(R.id.ip_box);
                        RegisterScreen.ipAddress = ip_box.getText().toString();
                    }});

                AlertDialog dialog = builder.create();
                dialog.show();
                EditText ip_box = (EditText)((AlertDialog)dialog).findViewById(R.id.ip_box);
                ip_box.setText(RegisterScreen.ipAddress);
                return true;
            case android.R.id.home:
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                Intent goToRegisterIntent = new Intent(this, WelcomeScreen.class);
                startActivity(goToRegisterIntent, bundle);
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == registerButton.getId())
        {
            String username = this.userName.getText().toString();
            String password = this.passWord.getText().toString();
            Toast.makeText(getApplicationContext(), "Registering...", Toast.LENGTH_SHORT).show();
            UserUtils.RegisterNewUser(username, password, this.requestQueue);
        }
    }
}
