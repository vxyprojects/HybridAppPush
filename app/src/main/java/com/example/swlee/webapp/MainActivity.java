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

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//
//
////        WebSettings websettings = webViewMain.getSettings();
////        WebSettings webSettings = webViewMain.getSettings();
////        webSettings.setJavaScriptEnabled(true);
////        webSettings.setBuiltInZoomControls(true);
////
////        webViewMain.setWebViewClient(new WebViewClient() {
////
////        });
////
////        webViewMain.setWebChromeClient(new WebChromeClient() {
////
////        });
////
////        webViewMain.loadUrl("http://m.dcinside.com/list.php?id=programming");
//
//
//// test
////        setContentView(R.layout.activity_main);
////        WebView WebView01 = (WebView) findViewById(R.id.webView01);
////        WebView01.setWebViewClient(new WebViewClient());
////
////        WebSettings webSettings = WebView01.getSettings();
////        webSettings.setJavaScriptEnabled(true);
////
////        WebView01.loadUrl("http://www.naver.com");
////        System.out.println("1111");
////        System.out.println("1111");
////        FirebaseMessaging.getInstance().subscribeToTopic("notice");
//
//        setContentView(R.layout.activity_main);
//        Button btnShowToken = (Button)findViewById(R.id.button_show_token);
//        Button btnCallRestApi = (Button)findViewById(R.id.call_rest_api);
//        btnShowToken.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String Token = FirebaseInstanceId.getInstance().getToken();
//                Log.d(Tag,"tocken is " +Token);
//                Toast.makeText(MainActivity.this,Token,Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        btnCallRestApi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Log.d(Tag,"tocken is " +Token);
////                Toast.makeText(MainActivity.this,Token,Toast.LENGTH_SHORT).show();
////                http://localhost:3000/get/cal_data
//                // post 인케이스일때는 post 를 적어준다  디폴트는 get이다
////                myConnection.setRequestMethod("POST");
//
////
////                String result; // 요청 결과를 저장할 변수.
////                RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
////                result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
////                return result;
////
//                //todo  해당  부분  메인스레드여서  이런식으로  처리가안되는것  같다  다른 형식으로 바꿔야할듯
////                new Thread () {
////                    public void run() {
////                        // 에러낸 코드를 여기로 옮겨 줘서 실행시켜준다.
////                    }
////                }.start();
////
////                URL httpbinEndpoint = null;
////                try {
//////                    httpbinEndpoint = new URL("http://localhost:3000/get/cal_data");
////                    httpbinEndpoint = new URL("https://localhost:3000/get/cal_data");
//////                    HttpURLConnection myConnection= (HttpURLConnection) httpbinEndpoint.openConnection();
////                    HttpsURLConnection myConnection= (HttpsURLConnection) httpbinEndpoint.openConnection();
////                    myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
////                    if (myConnection.getResponseCode() == 200) {
////                        Log.d(Tag,"res code is " +myConnection.getResponseCode());
////                        Toast.makeText(MainActivity.this,myConnection.getResponseCode(),Toast.LENGTH_SHORT).show();
////                        // Success
////                        // Further processing here
////                    } else {
////                        // Error handling code goes here
////                    }
////                } catch (MalformedURLException e) {
////                    e.printStackTrace();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
//
//            }
//        });
//    }
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
//                String Token  = "ttt";
//                Log.d(Tag,"tocken is " +Token);
//                Toast.makeText(MainActivity.this,Token,Toast.LENGTH_SHORT).show();


                // AsyncTask를 통해 HttpURLConnection 수행.
//                10.0.2.2:
//                NetworkTask networkTask = new NetworkTask("http://localhost:3000/get/cal_data", null);
//                NetworkTask networkTask = new NetworkTask("http://10.0.2.2:3000/get/cal_data", null);
//                NetworkTask networkTask = new NetworkTask("http://192.168.1.119:3000/get/cal_data", null);
//                NetworkTask networkTask = new NetworkTask("http://www.naver.com", null);
//                NetworkTask networkTask = new NetworkTask("http://172.30.1.14/get/cal_data", null);
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
//                 requestHttpURLConnection.getExam(); // 해당 URL로 부터 결과물을 얻어온다.
//                Log.d(Tag,"result is " +result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
//            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d(Tag,"result2 is " +s);
            TextView return_view = (TextView)findViewById(R.id.return_view);
            return_view.setText(s);
            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
//            tv_outPut.setText(s);
        }
    }
}
