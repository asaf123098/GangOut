package com.gangout;

import android.app.ActivityOptions;
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
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            Intent goToRegisterIntent = new Intent(this, RegisterScreen.class);
            startActivity(goToRegisterIntent, bundle);
        }
    }
}
