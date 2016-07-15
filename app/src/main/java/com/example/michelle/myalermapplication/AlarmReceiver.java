package com.example.michelle.myalermapplication;

/**
 * Created by michelle on 2016/07/01.
 */
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import com.example.michelle.myalermapplication.SubActivity;

public class AlarmReceiver extends BroadcastReceiver  {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent receivedIntent) {

        MediaPlayer mMP = MediaPlayer.create(context.getApplicationContext(), R.raw.gotown);
        int bid = receivedIntent.getIntExtra("intentId",0);
        // 共有データを取得
        Alarm alarm = Alarm.getInstance();

        int notificationId = receivedIntent.getIntExtra("notificationId", 0);
        NotificationManager myNotification = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent bootIntent = new Intent(context, SubActivity.class);
        PendingIntent contextIntent = PendingIntent.getActivity(context,bid,bootIntent,0);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.bell)
                .setContentTitle("アラーム設定時刻です。")
                .setContentText(receivedIntent.getCharSequenceExtra("todo"))
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(contextIntent);

        if(alarm.getVib()) {

            builder.setDefaults(Notification.DEFAULT_SOUND
                    | Notification.DEFAULT_VIBRATE);
        }
        myNotification.notify(notificationId, builder.build());

        System.out.println("バイブ確認"+alarm.getVib());
        // 通知時の音・バイブ
        if(!alarm.getVib()){


            mMP.start();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mMP.stop();
        }
        // タップするとキャンセル(消える)
        builder.setAutoCancel(true);
    }
}