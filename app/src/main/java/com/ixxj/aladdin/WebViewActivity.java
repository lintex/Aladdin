package com.ixxj.aladdin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

public class WebViewActivity extends Activity {
    private WebView mWebView;
    private TextView mTitle;
    private ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        mWebView.loadUrl(this.getIntent().getExtras().getString("url"));
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTitle.setText(title);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               WebViewActivity.this.finish();
            }
        });

    }


    private void initView() {
        mTitle = (TextView) findViewById(R.id.id_item_title);
        mWebView = (WebView) findViewById(R.id.webView);
        btn_back = (ImageButton) findViewById(R.id.id_item_back);

    }

}
