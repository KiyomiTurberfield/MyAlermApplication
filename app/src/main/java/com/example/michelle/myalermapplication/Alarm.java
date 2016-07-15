package com.example.michelle.myalermapplication;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Created by michelle on 2016/06/29.
 */
public class Alarm{

    // 自クラスをインスタンスに持つ
    private static Alarm alarm = null;

    // メンバ変数
    private boolean vib = false;//初期設定はオフ！

    public static Alarm getInstance() {
        if(alarm == null) {
            // インスタンスがnullの場合のみ生成する
            alarm = new Alarm();
        }
        return alarm;
    }
    private Alarm() {

    }


    public void setVib(boolean vib){
        this.vib = vib;
    }
    public boolean getVib(){
        return this.vib;
    }

}
