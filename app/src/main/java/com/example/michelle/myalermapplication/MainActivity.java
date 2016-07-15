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
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;
import java.util.Calendar;
import android.app.Activity;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int bid1 = 1;
    private static final int bid2 = 2;
    private static final int bid3 = 3;

    private Button button1, button2, button3;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView v  = (ImageView) findViewById(R.id.chicken);
        ImageView v2  = (ImageView) findViewById(R.id.cute_sunshine);

        Button btnVibON = (Button)findViewById(R.id.vibon);
        btnVibON.setOnClickListener(this);

        Button btnVibOFF = (Button)findViewById(R.id.viboff);
        btnVibOFF.setOnClickListener(this);


        // 10秒で繰り返しアラーム
        button1 = (Button)this.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // インテントの生成
                Intent intent = new Intent();
                intent.setClassName("com.example.michelle.myalermapplication","com.example.michelle.myalermapplication.SubActivity");

                intent.putExtra("intentId", 1);
                // SubActivity の起動
                startActivity(intent);


            }
        });
        // アラームの取り消し
        button2 = (Button)this.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                // インテントの生成

                intent.setClassName("com.example.michelle.myalermapplication","com.example.michelle.myalermapplication.SubActivity");

                intent.putExtra("intentId", 2);

                // SubActivity の起動
                startActivity(intent);

            }
        });

        // 日時を指定したアラーム
        button3 = (Button)this.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // インテントの生成
                Intent intent = new Intent();
                intent.setClassName("com.example.michelle.myalermapplication","com.example.michelle.myalermapplication.SubActivity");

                intent.putExtra("intentId", 3);

                // SubActivity の起動
                startActivity(intent);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.vibon:
                System.out.println("バイブON");
                // 共有するデータを設定
                Alarm alarmvib = Alarm.getInstance();
                alarmvib.setVib(true);
                System.out.println("バイブON押した" + alarmvib.getVib());

                Toast.makeText(MainActivity.this, "バイブレーションオン", Toast.LENGTH_SHORT).show();
                break;
            case R.id.viboff:
                System.out.println("バイブOFF");
                // 共有するデータを設定
                alarmvib = Alarm.getInstance();
                alarmvib.setVib(false);
                System.out.println("バイブOFF押した" + alarmvib.getVib());

                Toast.makeText(MainActivity.this, "バイブレーションオフ", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
