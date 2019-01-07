package com.gangout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity implements View.OnClickListener {

    private Button goToRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        goToRegisterButton = (Button)findViewById(R.id.go_to_register_bt);
        goToRegisterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == goToRegisterButton.getId()) {
            Intent goToRegisterIntent = new Intent(this, RegisterScreen.class);
            startActivity(goToRegisterIntent);
            finish();
        }
    }
}
