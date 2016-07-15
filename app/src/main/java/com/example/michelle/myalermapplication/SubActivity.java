package com.example.michelle.myalermapplication;

/**
 * Created by michelle on 2016/06/29.
 */
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class SubActivity extends AppCompatActivity implements View.OnClickListener{
//implementがエラー
    private InputMethodManager mInputMethodManager;
    private RelativeLayout mLayout;
    private int notificationId = 0;


    public int id;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub);

        Intent inte = getIntent();

        id = inte.getIntExtra("intentId", 0);
        System.out.println("受け取ったintentID:"+id);

        /*
        if(id == 1){
            Alarm alarm1 = new Alarm();
        } else if(id == 2){
            Alarm alarm2 = new Alarm();
        } else if (id == 3){
            Alarm alarm3 = new Alarm();
        }*/


        EditText edText = (EditText) findViewById(R.id.edText);
        mLayout = (RelativeLayout)findViewById(R.id.sublayout);
        //キーボード表示を制御するためのオブジェクト
        mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        Button btnSet = (Button)findViewById(R.id.set);
        btnSet.setOnClickListener(this);
        Button btnCancel = (Button)findViewById(R.id.cancel);
        btnCancel.setOnClickListener(this);
        /*
        Button btnVibON = (Button)findViewById(R.id.vibon);
        btnVibON.setOnClickListener(this);

        Button btnVibOFF = (Button)findViewById(R.id.viboff);
        btnVibOFF.setOnClickListener(this);
        */

        TimePicker tPicker = (TimePicker)findViewById(R.id.timePicker);
        tPicker.setIs24HourView(true);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        //ソフトキーボードを非表示にする
        mInputMethodManager.hideSoftInputFromWindow(mLayout.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        //背景にフォーカスを移す
        mLayout.requestFocus();
        return false;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {

        TimePicker tPicker = (TimePicker)findViewById(R.id.timePicker);
        tPicker.setIs24HourView(true);

        EditText edText = (EditText) findViewById(R.id.edText);

        //AlarmReceiverを呼び出すインテント
        Intent bootIntent = new Intent(SubActivity.this,AlarmReceiver.class);
        //追加データとして、Notificationの識別子を渡す
        bootIntent.putExtra("notificationId",notificationId);

        //追加データとして、やることを渡す
        bootIntent.putExtra("todo",edText.getText());

 /*AlarmManagerを使うことで、指定した時刻に指定した処理を実行することができる。
PendingIntentを使うと指定したタイミングでインテントを発行することができる。わー*/
        PendingIntent alarmIntent = PendingIntent.getBroadcast(SubActivity.this, id,
                bootIntent, 0);
        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        AlarmManager alarm2 = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        AlarmManager alarm3 = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        switch (v.getId()) {

            case R.id.set:
                //assert tPicker != null;
                int hour = tPicker.getHour();
                int minute = tPicker.getMinute();

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTIme = startTime.getTimeInMillis();

                switch (id) {
                    case 1:
                        alarm.setExact(
                                AlarmManager.RTC_WAKEUP,
                                alarmStartTIme,
                                alarmIntent

                        );
                    case 2:
                        alarm2.setExact(
                                AlarmManager.RTC_WAKEUP,
                                alarmStartTIme,
                                alarmIntent
                        );
                    case 3:
                        alarm3.setExact(
                                AlarmManager.RTC_WAKEUP,
                                alarmStartTIme,
                                alarmIntent
                        );
                }

                Toast.makeText(SubActivity.this, "通知をセット！", Toast.LENGTH_SHORT).show();
                notificationId++;

                break;
            case R.id.cancel:
                switch (id) {
                    case 1:
                        alarm.cancel(alarmIntent);
                    case 2:
                        alarm2.cancel(alarmIntent);
                    case 3:
                        alarm3.cancel(alarmIntent);
                }
                Toast.makeText(SubActivity.this, "通知をキャンセル！", Toast.LENGTH_SHORT).show();
                break;

            }
        }
}
