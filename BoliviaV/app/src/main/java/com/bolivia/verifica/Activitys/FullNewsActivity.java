package com.bolivia.verifica.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bolivia.verifica.R;

public class FullNewsActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);
        webView = (WebView) findViewById(R.id.webNews);

        String content = getIntent().getExtras().get("contenido").toString();
        Log.e("data",content);
        content = content.replace("&#8220;","'").replace("&#8221;","'");


        webView.loadDataWithBaseURL("https://boliviaverifica.bo","<style>img{display: inline;height: auto;max-width: 100%;}</style>" + content,"text/html; charset=utf-8", "UTF-8",null);


        webView.getSettings().setJavaScriptEnabled(true);




    }
}