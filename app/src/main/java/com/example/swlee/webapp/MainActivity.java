package com.example.swlee.webapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;




public class MainActivity extends AppCompatActivity {

    WebView webViewMain;
    // tag
    private static final String Tag  = "mainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


//        WebSettings websettings = webViewMain.getSettings();
//        WebSettings webSettings = webViewMain.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setBuiltInZoomControls(true);
//
//        webViewMain.setWebViewClient(new WebViewClient() {
//
//        });
//
//        webViewMain.setWebChromeClient(new WebChromeClient() {
//
//        });
//
//        webViewMain.loadUrl("http://m.dcinside.com/list.php?id=programming");


// test
//        setContentView(R.layout.activity_main);
//        WebView WebView01 = (WebView) findViewById(R.id.webView01);
//        WebView01.setWebViewClient(new WebViewClient());
//
//        WebSettings webSettings = WebView01.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//        WebView01.loadUrl("http://www.naver.com");
//        System.out.println("1111");
//        System.out.println("1111");
//        FirebaseMessaging.getInstance().subscribeToTopic("notice");

        setContentView(R.layout.activity_main);
        Button btnShowToken = (Button)findViewById(R.id.button_show_token);
        btnShowToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Token = FirebaseInstanceId.getInstance().getToken();
                Log.d(Tag,"tocken is " +Token);
                Toast.makeText(MainActivity.this,Token,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
