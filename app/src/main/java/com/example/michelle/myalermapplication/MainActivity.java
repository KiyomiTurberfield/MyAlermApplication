package com.example.michelle.myalermapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

//追加

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import java.util.Calendar;
import android.app.Activity;


public class MainActivity extends AppCompatActivity {

    private static final int bid1 = 1;
    private static final int bid2 = 2;
    private static final int bid3 = 3;

    private Button button1, button2, button3;
    private TextView textView;
    private int year, month, date, hour, minute, second, msecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        // 10秒で繰り返しアラーム
        button1 = (Button)this.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                // 5秒後に設定
                calendar.add(Calendar.SECOND, 5);

                Intent intent = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
                intent.putExtra("intentId", 1);

                // PendingIntentが同じ物の場合は上書きされてしまうので requestCode で区別する
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid1, intent, 0);

                // アラームをセットする
                AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                // 10秒で 繰り返し。スヌーズ設定するとき利用。消しちゃうとならない
                am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 10000, pending);

                // トーストで設定されたことをを表示
                Toast.makeText(getApplicationContext(), "ALARM 1", Toast.LENGTH_SHORT).show();

                // 無理やりですが、アプリを一旦終了します。この方法はバックグラウンドに移行させるための方便で推奨ではありません
                close();
                */

                // インテントの生成
                Intent intent = new Intent();
                intent.setClassName("com.example.michelle.myalermapplication","com.example.michelle.myalermapplication.SubActivity");

                intent.putExtra("intentId", 1);
                // SubActivity の起動
                startActivity(intent);


                //PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid1, intent, 0);

            }
        });
        // アラームの取り消し
        button2 = (Button)this.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
                Intent intent = new Intent();
                // インテントの生成
                //Intent intent2 = new Intent();
                intent.setClassName("com.example.michelle.myalermapplication","com.example.michelle.myalermapplication.SubActivity");

                intent.putExtra("intentId", 2);

                // SubActivity の起動
                startActivity(intent);
                //int intentId = 2;

                //PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid2, intent, 0);
            }
        });

        // 日時を指定したアラーム
        button3 = (Button)this.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // インテントの生成
                //Intent intent3 = new Intent();
                //intent3.setClassName("com.example.michelle.myalermapplication","com.example.michelle.myalermapplication.SubActivity");

                // SubActivity の起動
                //startActivity(intent3);

                //intent3.putExtra("intentId", 3);
                //PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid3, intent3, 0);
            }
        });

    }


    private void close(){
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // アラームの時間を設定
    private Calendar setAlarmTime() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 10);

        return calendar;
    }
}
