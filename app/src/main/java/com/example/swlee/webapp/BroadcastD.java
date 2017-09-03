package com.example.swlee.webapp; /**
 * Created by swlee on 2017. 9. 1..
 */

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class BroadcastD  extends BroadcastReceiver {


    String INTENT_ACTION = Intent.ACTION_BOOT_COMPLETED;
    private String call_api_data;

    @Override
    public void onReceive(Context context, Intent intent) {//알람 시간이 되었을때 onReceive를 호출함
        //NotificationManager 안드로이드 상태바에 메세지를 던지기위한 서비스 불러오고
        // AsyncTask를 통해 HttpURLConnection 수행.
        // todo  10분 마다  콜  -- 결국엔  call api 를  10분 마다  하고선  call 한부분에서 noti를 하냐 안하냐  처리
        //todo 현재 날짜 파라미터를  던져서  쿼리를  오늘 날짜의 일정데이타만  받아온다

        String makeUrl  = "http://172.30.1.11:3000/get/cal_data?current_date=2017-5-12";
//        MainActivity.NetworkTask networkTask = new MainActivity.NetworkTask("http://172.30.1.11:3000/get/cal_data", null);
        MainActivity.NetworkTask networkTask = new MainActivity.NetworkTask(makeUrl, null);
        // 이부분은  어싱크로 되면 안될듯 하다  싱크가 되서  처리 하고 나서  여기로 내려와야 할듯 하다
        try {
            //.execute()에 파라미터도  넘길수 있는듯
            this.call_api_data = networkTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("alarm_side","call_api_data is " +this.call_api_data);


//        SimpleDateFormat formatter_one = new SimpleDateFormat ( "EEE, dd MMM yyyy hh:mm:ss",Locale.ENGLISH );
//        SimpleDateFormat formatter_two = new SimpleDateFormat ( "yyyy-MM-dd" );
//        String inString = "Sun, 5 Dec 1999 00:07:21";
//        ParsePosition pos = new ParsePosition ( 0 );
//        Date frmTime = formatter_one.parse ( inString, pos );
//        String outString = formatter_two.format ( frmTime );


        // todo  call_api_data 검사 해서 noti를 해줄지 안해줄지  결정 해준다
        NotificationManager notificationmanager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.bar_icon).setTicker("HETT").setWhen(System.currentTimeMillis())
                .setNumber(1).setContentTitle("푸쉬 제목ttt").setContentText("푸쉬내용111")
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingIntent).setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationmanager.notify(1, builder.build());
        }
    }

}
