package com.example.swlee.webapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by swlee on 2017. 8. 29..
 */


public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = "FirebaseMsgService";

    public String title,contents,imgurl;



    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        sendPushNotification(remoteMessage.getData().get("message"));
        //test 시작
        Log.d(TAG,"from" +remoteMessage.getFrom());


        if (remoteMessage.getData().size()> 0){
        Log.d(TAG,"message data" +remoteMessage.getData());
        }


        if (remoteMessage.getNotification() != null){
            Log.d(TAG,"message body" +remoteMessage.getNotification().getBody());
//            sendPushNotification(remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }
    }


    /**
     *  test
     * @param body
     */
    private void sendNotification (String body){
       Intent intent  = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent =PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri notifiSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bar_icon)
                .setContentTitle("firebase cloud message by swlee")
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(notifiSound)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void sendPushNotification(String message) {



        System.out.println("received message : " + message);


        try {

            JSONObject jsonRootObject = new JSONObject(message);
            title = jsonRootObject.getString("title");
            contents = jsonRootObject.getString("contents");
            imgurl = jsonRootObject.getString("imgurl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = getBitmapFromURL(imgurl);
        Intent intent = new Intent(this, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
        PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)

                .setSmallIcon(R.drawable.bar_icon)

                .setLargeIcon(bitmap)

                .setContentTitle(title)

                .setContentText(contents)

                .setAutoCancel(true)

                .setSound(defaultSoundUri).setLights(000000255,500,2000)

                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);



        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);

        PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");

        wakelock.acquire(5000);





        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }







    public Bitmap getBitmapFromURL(String strURL) {

        try {

            URL url = new URL(strURL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);

            connection.connect();

            InputStream input = connection.getInputStream();

            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            return myBitmap;

        } catch (IOException e) {

            e.printStackTrace();

            return null;

        }

    }

}



