package com.example.swlee.webapp;

import android.content.ContentValues;
import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


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
        Button btnCallRestApi = (Button)findViewById(R.id.call_rest_api);
        btnShowToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Token = FirebaseInstanceId.getInstance().getToken();
                Log.d(Tag,"tocken is " +Token);
                Toast.makeText(MainActivity.this,Token,Toast.LENGTH_SHORT).show();
            }
        });


        btnCallRestApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // AsyncTask를 통해 HttpURLConnection 수행.
                NetworkTask networkTask = new NetworkTask("http://172.30.1.14:3000/get/cal_data", null);
                networkTask.execute();
            }
        });
    }


    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
//            Log.d(Tag,"tocken is " +"NetworkTask consturctor");
        }

        @Override
        protected String doInBackground(Void... params) {

            String result = null; // 요청 결과를 저장할 변수.
//            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
//            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            RequestHttpURLGet requestHttpURLConnection = new RequestHttpURLGet();
            try {
                result = requestHttpURLConnection.getExam(); // 해당 URL로 부터 결과물을 얻어온다.
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d(Tag,"result2 is " +s);
            TextView return_view = (TextView)findViewById(R.id.return_view);
            return_view.setText(s);
        }
    }
}
