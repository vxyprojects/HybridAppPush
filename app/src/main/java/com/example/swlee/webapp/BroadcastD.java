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

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class BroadcastD  extends BroadcastReceiver {


    String INTENT_ACTION = Intent.ACTION_BOOT_COMPLETED;
    private String call_api_data;

    @Override
    public void onReceive(Context context, Intent intent) {//알람 시간이 되었을때 onReceive를 호출함

        //현재 날짜
        String current_date =this.getCurrentData();
        //NotificationManager 안드로이드 상태바에 메세지를 던지기위한 서비스 불러오고
        // AsyncTask를 통해 HttpURLConnection 수행.
        // todo  10분 마다  콜  -- 결국엔  call api 를  10분 마다  하고선  call 한부분에서 noti를 하냐 안하냐  처리
        //todo 현재 날짜 파라미터를  던져서  쿼리를  오늘 날짜의 일정데이타만  받아온다
//        String makeUrl  = "http://172.30.1.11:3000/get/cal_data?current_date=2017-5-12";
        String makeUrl  = "http://172.30.1.11:3000/get/cal_data?current_date="+ current_date;
        MainActivity.NetworkTask networkTask = new MainActivity.NetworkTask(makeUrl, null);
        // 이부분은  어싱크로 되면 안될듯 하다  싱크가 되서  처리 하고 나서  여기로 내려와야 할듯 하다
        try {
            //.execute()에 파라미터도  넘길수 있는듯
            this.call_api_data = networkTask.execute().get();
            //todo  값이 오는것은 확인 데이타 형식 배열 형태로  바꿔서 noti를 해줘야하는지  로직 쫘줘야함
            //todo 웹쪽에서 noti 며칠전 또는 몇시간 후 이런것 입력하는창  처리 해줘야함

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("alarm_side","call_api_data is " +this.call_api_data);



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


    /**
     * 현재날짜 구해주는  함수
     * @return
     */
    private String getCurrentData(){

        Calendar cal = Calendar.getInstance();
        //현재 년도, 월, 일
        int year = cal.get ( cal.YEAR );
        int month = cal.get ( cal.MONTH ) + 1 ;
        int date = cal.get ( cal.DATE ) ;
        //todo 이거 당일날로 가져오면 안되겠구나 ... 며칠전 알림 최대값을 가져와야됨 최대 기간 만큼은 select 해서 결과값을  가져와서  비교해서 notify 해주게끔 처리 해줘야한다
        String makeUrl =String.valueOf(year) +"-"+ String.valueOf(month) +"-"+String.valueOf(date);
        Log.d("currentdate","currentdate is " +makeUrl);
        return makeUrl;
//        return "";
    }

}
