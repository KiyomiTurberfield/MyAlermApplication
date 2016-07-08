package com.example.michelle.myalermapplication;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Created by michelle on 2016/06/29.
 */
public class Alarm{

    String tittle = "アラーム";
    int hour;
    int min;
    boolean vib = true;//初期設定はオン！

    public void setTittle (String tittle){
        this.tittle = tittle;
    }
    public String getTittle (){
        return this.tittle;
    }

    public void setAHour(int hour){
        this.hour =hour;
    }
    public int getAHour(){
        return this.hour;
    }

    public void setAMin(int min){
        this.min = min;
    }
    public int getAMin() {
        return this.min;
    }

    public void setVib(boolean vib){
        this.vib = vib;
    }
    public boolean getVib(){
        return this.vib;
    }

}
