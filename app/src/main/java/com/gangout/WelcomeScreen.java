package com.gangout;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class WelcomeScreen extends AppCompatActivity implements View.OnClickListener {

    private Button goToRegisterButton;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome);

//        goToRegisterButton = (Button)findViewById(R.id.go_to_register_bt);
//        goToRegisterButton.setOnClickListener(this);

        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        webView.loadUrl("http://192.168.1.28:5500/test.html");
        setContentView(webView);
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
