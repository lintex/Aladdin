package com.ixxj.aladdin;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView.loadUrl(this.getIntent().getExtras().getString("url"));

    }


    private void initView() {
        mWebView = (WebView) findViewById(R.id.webView);


    }
}
