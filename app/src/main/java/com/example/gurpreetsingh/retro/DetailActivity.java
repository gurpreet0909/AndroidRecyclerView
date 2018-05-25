package com.example.gurpreetsingh.retro;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by gurpreetsingh on 24/12/17.
 */

public class DetailActivity extends AppCompatActivity{

    WebView webView1;
    ProgressBar progressBar1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.detail_activity_layout);

      webView1 = findViewById(R.id.webView);
      progressBar1= findViewById(R.id.progressBar);


        webView1.setVisibility(View.INVISIBLE);
        webView1.getSettings().getJavaScriptEnabled();
        webView1.setWebChromeClient(new WebChromeClient());


        webView1.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);

                progressBar1.setVisibility(View.INVISIBLE);
                webView1.setVisibility(View.VISIBLE);


            }
        });
        webView1.loadUrl(getIntent().getStringExtra("url"));
    }
}
