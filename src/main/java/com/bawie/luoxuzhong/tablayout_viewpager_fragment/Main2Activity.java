package com.bawie.luoxuzhong.tablayout_viewpager_fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String url = "";
        Intent intent = getIntent();
        if (intent!=null){
             url = intent.getStringExtra("key");
        }

        WebView webView = (WebView) findViewById(R.id.main2WebView);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
