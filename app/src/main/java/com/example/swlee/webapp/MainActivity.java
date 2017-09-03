package com.example.swlee.webapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
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
import java.util.Calendar;

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
//        WebSettings webSettings = WebView01.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        WebView01.loadUrl("http://www.naver.com");
//        FirebaseMessaging.getInstance().subscribeToTopic("notice");

        setContentView(R.layout.activity_main);
        new AlarmExecute(getApplicationContext()).Alarm();

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

                Log.d("tag","ffff");
                // AsyncTask를 통해 HttpURLConnection 수행.
                NetworkTask networkTask = new NetworkTask("http://172.30.1.14:3000/get/cal_data", null);
                networkTask.execute();
            }
        });
    }


    public static class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;
        private String returnData;

        public NetworkTask(String url, ContentValues values)
        {
            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params)
        {
            String result = null; // 요청 결과를 저장할 변수.
//            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
//            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
            RequestHttpURLGet requestHttpURLConnection = new RequestHttpURLGet();
            try {
//                Log.d("tag","ttt");
                result = requestHttpURLConnection.getExam(); // 해당 URL로 부터 결과물을 얻어온다.
                Log.d("result",result);
                this.returnData = result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
//            this.returnData = s;
//            TextView return_view = (TextView)findViewById(R.id.return_view);
//            return_view.setText(s);
        }

        public String getData() {

            return this.returnData;
        }
    }



    //push - 특정 시간에  보내지게끔 해주는 클래스
    public class AlarmExecute {

        // tag
        private static final String Tag  = "AlarmExecute";
        private Context context;
        private String call_api_data;
        public AlarmExecute(Context context) {
            this.context=context;
        }
        public void Alarm() {
            AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

            Log.d(Tag,"testsss");
            Intent intent = new Intent(MainActivity.this, BroadcastD.class);
            PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
            Calendar calendar = Calendar.getInstance();
            //알람시간 calendar에 set해주기
//            // todo  10분 마다  콜  -- 결국엔  call api 를  10분 마다  하고선  call 한부분에서 noti를 하냐 안하냐  처리 --- 이건 시작하자 마자 10분한번 실행하겠구나
//            calendar.add(Calendar.MINUTE, 10);
//            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), 0);
            //알람 예약
//            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
//            am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() ,
//                    10 * 60 * 1000,, sender);
            //10 분  마다  돌아가게끔  처리  -- 테스트
//            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                    10 * 60 * 1000,, sender);

            // 1분 마다  돌아가게끔 처리
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    1 * 60 * 1000, sender);



        }

    }
}
