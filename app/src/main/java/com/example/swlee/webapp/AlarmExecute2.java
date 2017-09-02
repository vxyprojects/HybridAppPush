package com.example.swlee.webapp; /**
 * Created by swlee on 2017. 9. 1..
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Map;

public class AlarmExecute2 {

    // tag
    private static final String Tag  = "AlarmExecute";
    private Context context;
    public AlarmExecute2(Context context) {
        this.context=context;
    }
    public void Alarm() {
//        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(MainActivity.this, BroadcastD.class);
//
//        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
//
//        Calendar calendar = Calendar.getInstance();
//        //알람시간 calendar에 set해주기
//
//        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 23, 12, 0);
//
//        //알람 예약
//        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    }

}
